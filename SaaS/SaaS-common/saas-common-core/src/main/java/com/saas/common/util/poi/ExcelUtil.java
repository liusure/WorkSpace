package com.saas.common.util.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saas.common.config.LmfConfig;
import com.saas.common.core.annotation.Excel;
import com.saas.common.core.annotation.Excel.ColumnType;
import com.saas.common.core.annotation.Excel.Type;
import com.saas.common.core.annotation.Excels;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.text.Convert;
import com.saas.common.dict.DictUtils;
import com.saas.common.exception.CustomException;
import com.saas.common.util.DateUtils;
import com.saas.common.util.Reflections;
import com.saas.common.util.file.FileTypeUtils;
import com.saas.common.util.file.ImageUtils;

/**
 * Excel????????????
 */
public class ExcelUtil<T> {
	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * Excel sheet?????????????????????65536
	 */
	public static final int sheetSize = 65536;

	/**
	 * ???????????????
	 */
	private String sheetName;

	/**
	 * ???????????????
	 */
	private Workbook wb;

	/**
	 * ???????????????
	 */
	private Sheet sheet;

	/**
	 * ????????????
	 */
	private Map<String, CellStyle> styles;

	/**
	 * ????????????
	 */
	private List<PoiField> defaultFields;

	private List<PoiField> importFields;

	private List<PoiField> exportFields;

	/**
	 * ????????????
	 */
	private short maxHeight;

	/**
	 * ????????????
	 */
	private Map<Integer, Double> statistics = new HashMap<Integer, Double>();

	List<T> list;

	/**
	 * ????????????
	 */
	private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

	/**
	 * ????????????
	 */
	public Class<T> clazz;

	public ExcelUtil(Class<T> clazz, List<PoiField> fields) {
		this.clazz = clazz;
		this.defaultFields = fields;
		createExcelField();
	}

	public ExcelUtil(Class<T> clazz, String... props) {
		this.clazz = clazz;
		this.defaultFields = new ArrayList<>();
		createDefaultExcelField();

		if (props != null && props.length > 0) {
			this.defaultFields = this.defaultFields.stream()
					.filter(field -> StringUtils.equalsAny(field.getPropName(), props)).collect(Collectors.toList());
		}

		createExcelField();
	}

	private void createDefaultExcelField() {
		List<Field> tempFields = new ArrayList<>();
		@SuppressWarnings("rawtypes")
		Class clasz = clazz;
		while (clasz != null) {
			tempFields.addAll(Arrays.asList(clasz.getDeclaredFields()));
			clasz = clasz.getSuperclass();
		}

		for (Field field : tempFields) {
			// ?????????
			if (field.isAnnotationPresent(Excel.class)) {
				putToField(field, field.getAnnotation(Excel.class));
			}

			// ?????????
			if (field.isAnnotationPresent(Excels.class)) {
				Excels attrs = field.getAnnotation(Excels.class);
				Excel[] excels = attrs.value();
				for (Excel excel : excels) {
					putToField(field, excel);
				}
			}
		}
	}

	/**
	 * ?????????????????????
	 */
	private void putToField(Field field, Excel attr) {
		if (attr != null) {
			PoiField poiField = new PoiField();
			poiField.setAlign(attr.align());
			poiField.setCellType(attr.cellType());
			poiField.setCombo(attr.combo());
			poiField.setDateFormat(attr.dateFormat());
			poiField.setDefaultValue(attr.defaultValue());
			poiField.setDictType(attr.dictType());
			poiField.setExport(attr.isExport());
			poiField.setHeight(attr.height());
			poiField.setName(attr.name());
			poiField.setNeedStatistics(attr.isStatistics());
			poiField.setPrompt(attr.prompt());
			poiField.setPropName(field.getName());
			poiField.setReadConverterExp(attr.readConverterExp());
			poiField.setRoundingMode(attr.roundingMode());
			poiField.setScale(attr.scale());
			poiField.setSeparator(attr.separator());
			poiField.setSort(attr.sort());
			poiField.setSuffix(attr.suffix());
			poiField.setTargetAttr(attr.targetAttr());
			poiField.setType(attr.type());
			poiField.setWidth(attr.width());
			this.defaultFields.add(poiField);
		}
	}

	private void init(List<T> list, String sheetName) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		this.list = list;
		this.sheetName = sheetName;
		createWorkbook();
	}

	/**
	 * ???excel???????????????????????????????????????list
	 * 
	 * @param is ?????????
	 * @return ???????????????
	 */
	public List<T> importExcel(InputStream is) throws Exception {
		return importExcel(StringUtils.EMPTY, is);
	}

	/**
	 * ???excel????????????????????????????????????list
	 * 
	 * @param sheetName ???????????????
	 * @param is        ?????????
	 * @return ???????????????
	 */
	public List<T> importExcel(String sheetName, InputStream is) throws Exception {
		this.wb = WorkbookFactory.create(is);
		List<T> list = new ArrayList<T>();
		Sheet sheet = null;
		if (StringUtils.isNotEmpty(sheetName)) {
			// ????????????sheet???,????????????sheet????????????.
			sheet = wb.getSheet(sheetName);
		} else {
			// ???????????????sheet??????????????????????????????1???sheet.
			sheet = wb.getSheetAt(0);
		}

		if (sheet == null) {
			throw new IOException("??????sheet?????????");
		}

		int rows = sheet.getPhysicalNumberOfRows();

		if (rows > 0) {
			// ????????????map????????????excel???????????????field.
			Map<String, Integer> cellMap = new HashMap<String, Integer>();
			// ????????????
			Row heard = sheet.getRow(0);
			for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
				Cell cell = heard.getCell(i);
				if (null != cell) {
					String value = this.getCellValue(heard, i).toString();
					cellMap.put(value, i);
				} else {
					cellMap.put(null, i);
				}
			}

			for (int i = 1; i < rows; i++) {
				// ??????2??????????????????,????????????????????????.
				Row row = sheet.getRow(i);
				T entity = null;
				for (int column = 0; column < this.importFields.size(); column++) {
					PoiField poiField = importFields.get(column);
					Object val = this.getCellValue(row, column);

					// ??????????????????????????????.
					entity = (entity == null ? clazz.newInstance() : entity);

					// ????????????,??????????????????????????????.
					Field field = Reflections.getAccessibleField(entity, poiField.getPropName());

					Class<?> fieldType = field.getType();

					if (StringUtils.isNotEmpty(poiField.getReadConverterExp())) {
						val = reverseByExp(Convert.toStr(val), poiField.getReadConverterExp(), poiField.getSeparator());
					} else if (StringUtils.isNotEmpty(poiField.getDictType())) {
						val = reverseDictByExp(Convert.toStr(val), poiField.getDictType(), poiField.getSeparator());
					}

					if (String.class == fieldType) {
						String s = Convert.toStr(val);
						if (StringUtils.endsWith(s, ".0")) {
							val = StringUtils.substringBefore(s, ".0");
						} else {
							String dateFormat = poiField.getDateFormat();
							if (StringUtils.isNotEmpty(dateFormat)) {
								val = DateUtils.parseDateToStr(dateFormat, (Date) val);
							} else {
								val = Convert.toStr(val);
							}
						}
					} else if ((Integer.TYPE == fieldType || Integer.class == fieldType)
							&& StringUtils.isNumeric(Convert.toStr(val))) {
						val = Convert.toInt(val);
					} else if (Long.TYPE == fieldType || Long.class == fieldType) {
						val = Convert.toLong(val);
					} else if (Double.TYPE == fieldType || Double.class == fieldType) {
						val = Convert.toDouble(val);
					} else if (Float.TYPE == fieldType || Float.class == fieldType) {
						val = Convert.toFloat(val);
					} else if (BigDecimal.class == fieldType) {
						val = Convert.toBigDecimal(val);
					} else if (Date.class == fieldType) {
						if (val instanceof String) {
							val = DateUtils.parseDate(val);
						} else if (val instanceof Double) {
							val = DateUtil.getJavaDate((Double) val);
						}
					} else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
						val = Convert.toBool(val, false);
					}
					if (null != fieldType) {
						String propertyName = field.getName();
						if (StringUtils.isNotEmpty(poiField.getTargetAttr())) {
							propertyName = field.getName() + "." + poiField.getTargetAttr();
						}
						Reflections.invokeSetter(entity, propertyName, val);
					}
				}
				list.add(entity);
			}
		}
		return list;
	}

	/**
	 * ???list???????????????????????????????????????excel??????
	 * 
	 * @param list      ??????????????????
	 * @param sheetName ??????????????????
	 * @return ??????
	 */
	public AjaxResult exportExcel(List<T> list, String sheetName) {
		this.init(list, sheetName);
		return exportExcel();
	}

	/**
	 * ???list???????????????????????????????????????excel??????
	 * 
	 * @param sheetName ??????????????????
	 * @return ??????
	 */
	public AjaxResult importTemplateExcel(String sheetName) {
		this.init(null, sheetName);
		return exportExcel();
	}

	public AjaxResult exportExcel() {
		OutputStream out = null;
		try {
			// ????????????????????????sheet.
			double sheetNo = Math.ceil(list.size() / sheetSize);

			for (int index = 0; index <= sheetNo; index++) {
				createSheet(sheetNo, index);

				// ????????????
				Row row = sheet.createRow(0);
				int column = 0;
				// ?????????????????????????????????
				for (PoiField os : this.exportFields) {
					this.createCell(os, row, column++);
				}

				fillExcelData(list, index, row, this.exportFields);
				addStatisticsRow();

			}
			String filename = encodingFilename(sheetName);
			out = new FileOutputStream(getAbsoluteFile(filename));
			wb.write(out);
			return AjaxResult.success(filename);
		} catch (Exception e) {
			log.error("??????Excel??????{}", e.getMessage());
			throw new CustomException("??????Excel????????????????????????????????????");
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * ??????excel??????
	 * 
	 * @param index ??????
	 * @param row   ????????????
	 */
	private void fillExcelData(List<T> list, int index, Row row, List<PoiField> fields) {
		int startNo = index * sheetSize;
		int endNo = Math.min(startNo + sheetSize, list.size());
		for (int i = startNo; i < endNo; i++) {
			row = sheet.createRow(i + 1 - startNo);
			// ??????????????????.
			T vo = (T) list.get(i);
			int column = 0;
			for (PoiField field : fields) {
				this.addCell(field, row, vo, column++);
			}
		}
	}

	/**
	 * ???????????????
	 */
	private Cell addCell(PoiField attr, Row row, T vo, int column) {
		Cell cell = null;
		try {
			// ????????????
			row.setHeight(maxHeight);
			// ??????Excel?????????????????????????????????,??????????????????????????????,???????????????????????????.
			if (attr.isExport()) {
				// ??????cell
				cell = row.createCell(column);
				int align = attr.getAlign().value();
				cell.setCellStyle(styles.get("data" + (align >= 1 && align <= 3 ? align : "")));

				// ??????????????????????????????
				Object value = getTargetValue(vo, attr);
				String dateFormat = attr.getDateFormat();
				String readConverterExp = attr.getReadConverterExp();
				String separator = attr.getSeparator();
				String dictType = attr.getDictType();
				if (StringUtils.isNotEmpty(dateFormat) && value != null) {
					cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
				} else if (StringUtils.isNotEmpty(readConverterExp) && value != null) {
					cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
				} else if (StringUtils.isNotEmpty(dictType) && value != null) {
					cell.setCellValue(convertDictByExp(Convert.toStr(value), dictType, separator));
				} else if (value instanceof BigDecimal && -1 != attr.getScale()) {
					cell.setCellValue(
							(((BigDecimal) value).setScale(attr.getScale(), attr.getRoundingMode())).toString());
				} else {
					// ???????????????
					setCellVo(value, attr, cell);
				}
				addStatisticsData(column, Convert.toStr(value), attr);
			}
		} catch (Exception e) {
			log.error("??????Excel??????{}", e);
		}
		return cell;
	}

	/**
	 * ???????????????
	 */
	private void addStatisticsRow() {
		if (statistics.size() > 0) {
			Cell cell = null;
			Row row = sheet.createRow(sheet.getLastRowNum() + 1);
			Set<Integer> keys = statistics.keySet();
			cell = row.createCell(0);
			cell.setCellStyle(styles.get("total"));
			cell.setCellValue("??????");

			for (Integer key : keys) {
				cell = row.createCell(key);
				cell.setCellStyle(styles.get("total"));
				cell.setCellValue(DOUBLE_FORMAT.format(statistics.get(key)));
			}
			statistics.clear();
		}
	}

	/**
	 * ???????????????
	 */
	private Cell createCell(PoiField attr, Row row, int column) {
		// ?????????
		Cell cell = row.createCell(column);
		// ???????????????
		cell.setCellValue(attr.getName());
		setDataValidation(attr, row, column);
		cell.setCellStyle(styles.get("header"));
		return cell;
	}

	private void createSheet(double sheetNo, int index) {
		this.sheet = wb.createSheet();
		this.styles = createStyles(wb);
		// ????????????????????????.
		if (sheetNo == 0) {
			wb.setSheetName(index, sheetName);
		} else {
			wb.setSheetName(index, sheetName + index);
		}
	}

	/**
	 * ??????????????????
	 */
	private void setDataValidation(PoiField attr, Row row, int column) {
		if (attr.getName().indexOf("??????") >= 0) {
			sheet.setColumnWidth(column, 6000);
		} else {
			// ????????????
			sheet.setColumnWidth(column, (int) ((attr.getWidth() + 0.72) * 256));
		}
		// ???????????????????????????????????????????????????.
		if (StringUtils.isNotEmpty(attr.getPrompt())) {
			// ??????????????????2-101?????????.
			setXSSFPrompt(sheet, "", attr.getPrompt(), 1, 100, column, column);
		}
		// ???????????????combo???????????????????????????????????????
		if (attr.getCombo().length > 0) {
			// ??????????????????2-101???????????????????????????.
			setXSSFValidation(sheet, attr.getCombo(), 1, 100, column, column);
		}
	}

	/**
	 * ?????????????????????
	 * 
	 * @param value ????????????
	 * @param attr  ????????????
	 * @param cell  ???????????????
	 */
	private void setCellVo(Object value, PoiField attr, Cell cell) {
		if (ColumnType.STRING == attr.getCellType()) {
			cell.setCellValue(value == null ? attr.getDefaultValue() : value + attr.getSuffix());
		} else if (ColumnType.NUMERIC == attr.getCellType()) {
			cell.setCellValue(
					StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value) : Convert.toInt(value));
		} else if (ColumnType.IMAGE == attr.getCellType()) {
			ClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) cell.getColumnIndex(),
					cell.getRow().getRowNum(), (short) (cell.getColumnIndex() + 1), cell.getRow().getRowNum() + 1);
			String imagePath = Convert.toStr(value);
			if (StringUtils.isNotEmpty(imagePath)) {
				byte[] data = ImageUtils.getImage(imagePath);
				getDrawingPatriarch(cell.getSheet()).createPicture(anchor,
						cell.getSheet().getWorkbook().addPicture(data, getImageType(data)));
			}
		}
	}

	/**
	 * ??????????????????
	 */
	private void addStatisticsData(Integer index, String text, PoiField entity) {
		if (entity != null && entity.isNeedStatistics()) {
			Double temp = 0D;
			if (!statistics.containsKey(index)) {
				statistics.put(index, temp);
			}
			try {
				temp = Double.valueOf(text);
			} catch (NumberFormatException e) {
			}
			statistics.put(index, statistics.get(index) + temp);
		}
	}

	/**
	 * ????????????????????????
	 */
	private void createExcelField() {

		this.defaultFields = this.defaultFields.stream().sorted(Comparator.comparing(field -> field.getSort()))
				.collect(Collectors.toList());

		this.exportFields = this.defaultFields.stream()
				.filter(field -> field.getType().equals(Type.ALL) || field.getType().equals(Type.EXPORT))
				.collect(Collectors.toList());

		this.importFields = this.defaultFields.stream()
				.filter(field -> field.getType().equals(Type.ALL) || field.getType().equals(Type.IMPORT))
				.collect(Collectors.toList());
		this.maxHeight = getRowHeight();
	}

	/**
	 * ?????????????????????
	 */
	private void createWorkbook() {
		this.wb = new SXSSFWorkbook(500);
	}

	/**
	 * ??????????????????????????????
	 */
	private short getRowHeight() {
		double maxHeight = 0;
		for (PoiField field : this.defaultFields) {
			maxHeight = maxHeight > field.getHeight() ? maxHeight : field.getHeight();
		}
		return (short) (maxHeight * 20);
	}

	/**
	 * ????????????
	 */
	private static Drawing<?> getDrawingPatriarch(Sheet sheet) {
		if (sheet.getDrawingPatriarch() == null) {
			sheet.createDrawingPatriarch();
		}
		return sheet.getDrawingPatriarch();
	}

	/**
	 * ??????????????????,????????????????????????
	 */
	private int getImageType(byte[] value) {
		String type = FileTypeUtils.getFileExtendName(value);
		if ("JPG".equalsIgnoreCase(type)) {
			return Workbook.PICTURE_TYPE_JPEG;
		} else if ("PNG".equalsIgnoreCase(type)) {
			return Workbook.PICTURE_TYPE_PNG;
		}
		return Workbook.PICTURE_TYPE_JPEG;
	}

	/**
	 * ??????bean???????????????
	 * 
	 * @param vo    ????????????
	 * @param field ??????
	 * @param excel ??????
	 * @return ??????????????????
	 * @throws Exception
	 */
	private Object getTargetValue(T vo, PoiField excel) throws Exception {
		Object o = Reflections.getFieldValue(vo, excel.getPropName());
		if (StringUtils.isNotEmpty(excel.getTargetAttr())) {
			String target = excel.getTargetAttr();
			if (target.indexOf(".") > -1) {
				String[] targets = target.split("[.]");
				for (String name : targets) {
					o = getValue(o, name);
				}
			} else {
				o = getValue(o, target);
			}
		}
		return o;
	}

	/**
	 * ??????????????????get???????????????????????????
	 * 
	 * @param o
	 * @param name
	 * @return value
	 * @throws Exception
	 */
	private Object getValue(Object o, String name) throws Exception {
		if (o != null && StringUtils.isNotEmpty(name)) {
			o = Reflections.getFieldValue(o, name);
		}
		return o;
	}

	/**
	 * ??????????????????
	 * 
	 * @param row    ????????????
	 * @param column ?????????????????????
	 * @return ????????????
	 */
	private Object getCellValue(Row row, int column) {
		if (row == null) {
			return row;
		}
		Object val = "";
		try {
			Cell cell = row.getCell(column);
			if (null != cell) {
				if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
					val = cell.getNumericCellValue();
					if (DateUtil.isCellDateFormatted(cell)) {
						val = DateUtil.getJavaDate((Double) val); // POI Excel ??????????????????
					} else {
						if ((Double) val % 1 != 0) {
							val = new BigDecimal(val.toString());
						} else {
							val = new DecimalFormat("0").format(val);
						}
					}
				} else if (cell.getCellType() == CellType.STRING) {
					val = cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.BOOLEAN) {
					val = cell.getBooleanCellValue();
				} else if (cell.getCellType() == CellType.ERROR) {
					val = cell.getErrorCellValue();
				}

			}
		} catch (Exception e) {
			return val;
		}
		return val;
	}

	/**
	 * ??????????????? 0=???,1=???,2=??????
	 * 
	 * @param propertyValue ?????????
	 * @param converterExp  ????????????
	 * @param separator     ?????????
	 * @return ????????????
	 */
	private static String convertByExp(String propertyValue, String converterExp, String separator) {
		StringBuilder propertyString = new StringBuilder();
		String[] convertSource = converterExp.split(",");
		for (String item : convertSource) {
			String[] itemArray = item.split("=");
			if (StringUtils.containsAny(separator, propertyValue)) {
				for (String value : propertyValue.split(separator)) {
					if (itemArray[0].equals(value)) {
						propertyString.append(itemArray[1] + separator);
						break;
					}
				}
			} else {
				if (itemArray[0].equals(propertyValue)) {
					return itemArray[1];
				}
			}
		}
		return StringUtils.stripEnd(propertyString.toString(), separator);
	}

	/**
	 * ???????????????
	 * 
	 * @param dictValue ?????????
	 * @param dictType  ????????????
	 * @param separator ?????????
	 * @return ????????????
	 */
	private static String convertDictByExp(String dictValue, String dictType, String separator) {

		return DictUtils.getDictLabel(dictType, dictValue, separator);
	}

	/**
	 * ????????????????????????
	 * 
	 * @param dictLabel ????????????
	 * @param dictType  ????????????
	 * @param separator ?????????
	 * @return ?????????
	 */
	private static String reverseDictByExp(String dictLabel, String dictType, String separator) {

		return DictUtils.getDictValue(dictType, dictLabel, separator);
	}

	/**
	 * ????????????????????????????????????????????????,???????????????.
	 * 
	 * @param sheet    ????????????sheet.
	 * @param textlist ????????????????????????
	 * @param firstRow ?????????
	 * @param endRow   ?????????
	 * @param firstCol ?????????
	 * @param endCol   ?????????
	 * @return ????????????sheet.
	 */
	private static void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		DataValidationHelper helper = sheet.getDataValidationHelper();
		// ????????????????????????
		DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
		// ????????????????????????????????????????????????,?????????????????????????????????????????????????????????????????????
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		// ?????????????????????
		DataValidation dataValidation = helper.createValidation(constraint, regions);
		// ??????Excel???????????????
		if (dataValidation instanceof XSSFDataValidation) {
			dataValidation.setSuppressDropDownArrow(true);
			dataValidation.setShowErrorBox(true);
		} else {
			dataValidation.setSuppressDropDownArrow(false);
		}

		sheet.addValidationData(dataValidation);
	}

	/**
	 * ?????? POI XSSFSheet ???????????????
	 * 
	 * @param sheet         ??????
	 * @param promptTitle   ????????????
	 * @param promptContent ????????????
	 * @param firstRow      ?????????
	 * @param endRow        ?????????
	 * @param firstCol      ?????????
	 * @param endCol        ?????????
	 */
	private static void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
			int firstCol, int endCol) {
		DataValidationHelper helper = sheet.getDataValidationHelper();
		DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		DataValidation dataValidation = helper.createValidation(constraint, regions);
		dataValidation.createPromptBox(promptTitle, promptContent);
		dataValidation.setShowPromptBox(true);
		sheet.addValidationData(dataValidation);
	}

	/**
	 * ??????????????????
	 * 
	 * @param wb ???????????????
	 * @return ????????????
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		// ??????????????????,??????????????????excel???????????????
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		Font totalFont = wb.createFont();
		totalFont.setFontName("Arial");
		totalFont.setFontHeightInPoints((short) 10);
		style.setFont(totalFont);
		styles.put("total", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(HorizontalAlignment.LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(HorizontalAlignment.CENTER);
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(HorizontalAlignment.RIGHT);
		styles.put("data3", style);

		return styles;
	}

	/**
	 * ???????????????
	 */
	private static String encodingFilename(String filename) {
		filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
		return filename;
	}

	/**
	 * ??????????????????
	 * 
	 * @param filename ????????????
	 */
	private static String getAbsoluteFile(String filename) {
		String downloadPath = LmfConfig.getDownloadPath() + filename;
		File desc = new File(downloadPath);
		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		return downloadPath;
	}

	/**
	 * ??????????????? ???=0,???=1,??????=2
	 * 
	 * @param propertyValue ?????????
	 * @param converterExp  ????????????
	 * @param separator     ?????????
	 * @return ????????????
	 */
	private static String reverseByExp(String propertyValue, String converterExp, String separator) {
		StringBuilder propertyString = new StringBuilder();
		String[] convertSource = converterExp.split(",");
		for (String item : convertSource) {
			String[] itemArray = item.split("=");
			if (StringUtils.containsAny(separator, propertyValue)) {
				for (String value : propertyValue.split(separator)) {
					if (itemArray[1].equals(value)) {
						propertyString.append(itemArray[0] + separator);
						break;
					}
				}
			} else {
				if (itemArray[1].equals(propertyValue)) {
					return itemArray[0];
				}
			}
		}
		return StringUtils.stripEnd(propertyString.toString(), separator);
	}

}
