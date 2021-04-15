/**
 * 
 */
package com.saas.common.util.poi;

import java.math.BigDecimal;

import com.saas.common.core.annotation.Excel.Align;
import com.saas.common.core.annotation.Excel.ColumnType;
import com.saas.common.core.annotation.Excel.Type;

/**
 * 字段定义
 * 
 * @author bruce
 *
 */
public class PoiField {

	/**
	 * 字段映射属性
	 */
	private String propName;

	/**
	 * 导出到Excel中的名字
	 */
	private String name = "";

	/**
	 * 导出到Excel中的排序
	 */
	private int sort;

	/**
	 * 日期格式, 如: yyyy-MM-dd
	 */
	private String dateFormat = "";

	/**
	 * 如果是字典类型，请设置字典的type值 (如: sys_user_sex)
	 */
	private String dictType = "";

	/**
	 * 读取内容转表达式 (如: 0=男,1=女,2=未知)
	 */
	private String readConverterExp = "";

	/**
	 * 分隔符，读取字符串组内容
	 */
	private String separator = ",";

	/**
	 * BigDecimal 精度 默认:-1(默认不开启BigDecimal格式化)
	 */
	private int scale = -1;

	/**
	 * BigDecimal 舍入规则 默认:BigDecimal.ROUND_HALF_EVEN
	 */
	private int roundingMode = BigDecimal.ROUND_HALF_EVEN;

	/**
	 * 导出类型（0数字 1字符串）
	 */
	private ColumnType cellType = ColumnType.STRING;

	/**
	 * 导出时在excel中每个列的高度 单位为字符
	 */
	private double height = 14;

	/**
	 * 导出时在excel中每个列的宽 单位为字符
	 */
	private double width = 16;

	/**
	 * 文字后缀,如% 90 变成90%
	 */
	private String suffix = "";

	/**
	 * 当值为空时,字段的默认值
	 */
	private String defaultValue = "";

	/**
	 * 提示信息
	 */
	private String prompt = "";

	/**
	 * 导出字段对齐方式（0：默认；1：靠左；2：居中；3：靠右）
	 */
	private Align align = Align.AUTO;

	/**
	 * 设置只能选择不能输入的列内容.
	 */
	private String[] combo = {};

	/**
	 * 另一个类中的属性名称,支持多级获取,以小数点隔开
	 */
	private String targetAttr = "";

	/**
	 * 是否自动统计数据,在最后追加一行统计数据总和
	 */
	private boolean needStatistics = false;

	/**
	 * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
	 */
	private boolean export = true;

	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	private Type type = Type.ALL;

	public PoiField() {

	}

	public PoiField(String propName, String name, int sort) {
		this.propName = propName;
		this.name = name;
		this.sort = sort;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getReadConverterExp() {
		return readConverterExp;
	}

	public void setReadConverterExp(String readConverterExp) {
		this.readConverterExp = readConverterExp;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getRoundingMode() {
		return roundingMode;
	}

	public void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}

	public ColumnType getCellType() {
		return cellType;
	}

	public void setCellType(ColumnType cellType) {
		this.cellType = cellType;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Align getAlign() {
		return align;
	}

	public void setAlign(Align align) {
		this.align = align;
	}

	public String[] getCombo() {
		return combo;
	}

	public void setCombo(String[] combo) {
		this.combo = combo;
	}

	public String getTargetAttr() {
		return targetAttr;
	}

	public void setTargetAttr(String targetAttr) {
		this.targetAttr = targetAttr;
	}

	public boolean isNeedStatistics() {
		return needStatistics;
	}

	public void setNeedStatistics(boolean needStatistics) {
		this.needStatistics = needStatistics;
	}

	public boolean isExport() {
		return export;
	}

	public void setExport(boolean export) {
		this.export = export;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
