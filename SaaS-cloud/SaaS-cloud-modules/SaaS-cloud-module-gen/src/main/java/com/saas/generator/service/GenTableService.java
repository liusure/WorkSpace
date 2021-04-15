package com.saas.generator.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.saas.common.constant.Constants;
import com.saas.common.core.text.CharsetKit;
import com.saas.common.exception.CustomException;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.common.util.UserContextHolder;
import com.saas.common.util.file.FileUtils;
import com.saas.generator.constant.GenConstants;
import com.saas.generator.domain.GenTable;
import com.saas.generator.domain.GenTableColumn;
import com.saas.generator.repository.GenTableDao;
import com.saas.generator.util.GenUtils;
import com.saas.generator.util.VelocityInitializer;
import com.saas.generator.util.VelocityUtils;

@Service
public class GenTableService extends BaseService<GenTable> {

	@Autowired
	private GenTableDao modelDao;

	@Autowired
	private DbTableService dbTableService;

	@Autowired
	private GenTableColumnService genTableColumnService;

	@Override
	protected BaseDao<GenTable> getModelDao() {

		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return false;
	}

	/**
	 * 导入表结构
	 * 
	 * @param tableList 导入表列表
	 */
	@Transactional
	public void importGenTable(List<GenTable> tableList) {
		Long operId = UserContextHolder.getCurrentUser().getId();
		try {
			for (GenTable table : tableList) {
				String tableName = table.getTableName();
				GenUtils.initTable(table, operId);
				save(table);

				// 保存列信息
				List<GenTableColumn> genTableColumns = dbTableService.selectDbTableColumnsByName(tableName);
				for (GenTableColumn column : genTableColumns) {
					GenUtils.initColumnField(column, table);
					genTableColumnService.save(column);
				}

			}
		} catch (Exception e) {
			throw new CustomException("导入失败：" + e.getMessage());
		}
	}

	/**
	 * 修改保存参数校验
	 * 
	 * @param genTable 业务信息
	 */
	public void validateEdit(GenTable genTable) {
		if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
			String options = JSON.toJSONString(genTable.getParams());
			JSONObject paramsObj = JSONObject.parseObject(options);
			if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
				throw new CustomException("树编码字段不能为空");
			} else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
				throw new CustomException("树父编码字段不能为空");
			} else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
				throw new CustomException("树名称字段不能为空");
			}
		}
	}

	/**
	 * 同步数据库
	 * 
	 * @param tableName 表名称
	 */
	@Transactional
	public void synchDb(Long tableId) {
		// 查询表信息
		GenTable table = getById(tableId);
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		table.setColumns(list);

		List<GenTableColumn> tableColumns = table.getColumns();
		List<String> tableColumnNames = tableColumns.stream().map(GenTableColumn::getColumnName)
				.collect(Collectors.toList());

		List<GenTableColumn> dbTableColumns = dbTableService.selectDbTableColumnsByName(table.getTableName());
		List<String> dbTableColumnNames = dbTableColumns.stream().map(GenTableColumn::getColumnName)
				.collect(Collectors.toList());

		dbTableColumns.forEach(column -> {
			if (!tableColumnNames.contains(column.getColumnName())) {
				GenUtils.initColumnField(column, table);
				genTableColumnService.save(column);
			}
		});

		List<GenTableColumn> delColumns = tableColumns.stream()
				.filter(column -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
		if (null != delColumns) {
			for (GenTableColumn column : delColumns) {
				genTableColumnService.delete(column.getId());
			}
		}
	}

	/**
	 * 修改业务
	 * 
	 * @param genTable 业务信息
	 * @return 结果
	 */
	@Transactional
	public void updateGenTable(GenTable genTable) {
		Long operId = UserContextHolder.getCurrentUser().getId();
		String options = JSON.toJSONString(genTable.getParams());
		genTable.setOptions(options);
		save(genTable);
		for (GenTableColumn cenTableColumn : genTable.getColumns()) {
			genTableColumnService.save(cenTableColumn);
		}

	}

	/*
	 * 删除业务对象
	 * 
	 * @param tableIds 需要删除的数据ID
	 * 
	 * @return 结果
	 */

	@Transactional
	public void deleteGenTableByIds(Long[] tableIds) {
		Long operId = UserContextHolder.getCurrentUser().getId();
		for (Long tableId : tableIds) {
			this.physicalDelete(this.getById(tableId));
			Map<String, Object> params = new HashMap<>();
			params.put("EQ_tableId", tableId);
			List<GenTableColumn> list = genTableColumnService.getList(params);
			for (GenTableColumn column : list) {
				genTableColumnService.physicalDelete(column);
			}
		}
	}

	/**
	 * 预览代码
	 * 
	 * @param tableId 表编号
	 * @return 预览数据列表
	 */

	public Map<String, String> previewCode(Long tableId) {
		Map<String, String> dataMap = new LinkedHashMap<>();
		// 查询表信息
		GenTable table = getById(tableId);
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		table.setColumns(list);

		// 查询列信息
		List<GenTableColumn> columns = table.getColumns();
		setPkColumn(table, columns);
		VelocityInitializer.initVelocity();

		VelocityContext context = VelocityUtils.prepareContext(table);

		// 获取模板列表
		List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
			dataMap.put(template, sw.toString());
		}
		return dataMap;
	}

	/**
	 * 设置主键列信息
	 * 
	 * @param table   业务表信息
	 * @param columns 业务字段列表
	 */
	public void setPkColumn(GenTable table, List<GenTableColumn> columns) {
		for (GenTableColumn column : columns) {
			if (column.isPk()) {
				table.setPkColumn(column);
				break;
			}
		}
		if (null == table.getPkColumn()) {
			table.setPkColumn(columns.get(0));
		}
	}

	/**
	 * 生成代码（下载方式）
	 * 
	 * @param tableName 表名称
	 * @return 数据
	 */
	public byte[] downloadCode(Long tableId) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		generatorCode(tableId, zip);
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	public byte[] downloadCode(Long[] tableIds) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for (Long tableName : tableIds) {
			generatorCode(tableName, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	/**
	 * 生成代码（自定义路径）
	 * 
	 * @param tableName 表名称
	 */
	public void generatorCode(Long tableId) {
		GenTable table = getById(tableId);
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		table.setColumns(list);
		// 查询列信息
		List<GenTableColumn> columns = table.getColumns();
		setPkColumn(table, columns);

		VelocityInitializer.initVelocity();

		VelocityContext context = VelocityUtils.prepareContext(table);

		// 获取模板列表
		List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
		for (String template : templates) {
			if (!StringUtils.containsAny(template, "sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm")) {
				// 渲染模板
				StringWriter sw = new StringWriter();
				Template tpl = Velocity.getTemplate(template, Constants.UTF8);
				tpl.merge(context, sw);
				try {
					String path = getGenPath(table, template);
					FileUtils.writeStringToFile(new File(path), sw.toString(), CharsetKit.UTF_8);
				} catch (IOException e) {
					throw new CustomException("渲染模板失败，表名：" + table.getTableName());
				}
			}
		}
	}

	/**
	 * 查询表信息并生成代码
	 */
	private void generatorCode(Long tableId, ZipOutputStream zip) {
		// 查询表信息

		GenTable table = getById(tableId);
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		table.setColumns(list);
		// 查询列信息
		List<GenTableColumn> columns = table.getColumns();
		setPkColumn(table, columns);

		VelocityInitializer.initVelocity();

		VelocityContext context = VelocityUtils.prepareContext(table);

		// 获取模板列表
		List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
				IOUtils.write(sw.toString(), zip, Constants.UTF8);
				IOUtils.closeQuietly(sw);
				zip.flush();
				zip.closeEntry();
			} catch (IOException e) {
				logger.error("渲染模板失败，表名：" + table.getTableName(), e);
			}
		}
	}

	/**
	 * 获取代码生成地址
	 * 
	 * @param table    业务表信息
	 * @param template 模板文件路径
	 * @return 生成地址
	 */
	public static String getGenPath(GenTable table, String template) {
		String genPath = table.getGenPath();
		if (StringUtils.equals(genPath, "/")) {
			return System.getProperty("user.dir") + File.separator + "src" + File.separator
					+ VelocityUtils.getFileName(template, table);
		}
		return genPath + File.separator + VelocityUtils.getFileName(template, table);
	}

}
