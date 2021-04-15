package com.saas.generator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.controller.BaseController;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.core.page.DataPage;
import com.saas.common.core.page.DataPageable;
import com.saas.common.core.text.Convert;
import com.saas.common.enums.BusinessType;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.generator.domain.GenTable;
import com.saas.generator.domain.GenTableColumn;
import com.saas.generator.service.DbTableService;
import com.saas.generator.service.GenTableColumnService;
import com.saas.generator.service.GenTableService;
import com.saas.log.annotation.Log;

/**
 * 代码生成 操作处理
 * 
 */
@RestController
@RequestMapping("/gen")
public class GenController extends BaseController {
	@Autowired
	private GenTableService genTableService;

	@Autowired
	private GenTableColumnService genTableColumnService;

	@Autowired
	private DbTableService dbTableService;

	/**
	 * 查询代码生成列表
	 */
	@PreAuthorize(hasPermi="tool:gen:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<GenTable> tables = genTableService.getListPage(pageable);
		return AjaxResult.success(tables);
	}

	/**
	 * 修改代码生成业务
	 */
	@PreAuthorize(hasPermi="tool:gen:query")
	@GetMapping(value = "/{tableId}")
	public AjaxResult getInfo(@PathVariable Long tableId) {
		GenTable table = genTableService.getById(tableId);
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		Map<String, Object> map = new HashMap<String, Object>();
		table.setColumns(list);
		map.put("info", table);
		map.put("rows", list);
		return AjaxResult.success(map);
	}

	/**
	 * 查询数据库列表
	 */
	@PreAuthorize(hasPermi="tool:gen:list")
	@GetMapping("/db/list")
	public AjaxResult dataList(GenTable genTable) {
		List<GenTable> list = dbTableService.selectDbTableList();
		return AjaxResult.success(list);
	}

	/**
	 * 查询数据表字段列表
	 */
	@SuppressWarnings("unchecked")
	@PreAuthorize(hasPermi="tool:gen:list")
	@GetMapping(value = "/column/{talbleId}")
	public AjaxResult columnList(Long tableId) {
		Map<String, Object> params = new HashMap<>();
		params.put("EQ_tableId", tableId);
		List<GenTableColumn> list = genTableColumnService.getList(params);
		DataPage<GenTableColumn> dataInfo = new DataPage(list, list.size());
		return AjaxResult.success(dataInfo);
	}

	/**
	 * 导入表结构（保存）
	 */
	@PreAuthorize(hasPermi="tool:gen:list")
	@Log(title = "代码生成", businessType = BusinessType.IMPORT)
	@PostMapping("/importTable")
	public AjaxResult importTableSave(String tables) {
		String[] tableNames = Convert.toStrArray(tables);
		// 查询表信息
		List<GenTable> tableList = dbTableService.selectDbTableListByNames(tableNames);
		genTableService.importGenTable(tableList);
		return AjaxResult.success();
	}

	/**
	 * 修改保存代码生成业务
	 */
	@PreAuthorize(hasPermi="tool:gen:edit")
	@Log(title = "代码生成", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult editSave(@Validated @RequestBody GenTable genTable) {
		genTableService.validateEdit(genTable);
		genTableService.updateGenTable(genTable);
		return AjaxResult.success();
	}

	/**
	 * 删除代码生成
	 */
	@PreAuthorize(hasPermi="tool:gen:remove")
	@Log(title = "代码生成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
	public AjaxResult remove(@PathVariable Long[] tableIds) {
		genTableService.deleteGenTableByIds(tableIds);
		return AjaxResult.success();
	}

	/**
	 * 预览代码
	 */
	@PreAuthorize(hasPermi="tool:gen:preview")
	@GetMapping("/preview/{tableId}")
	public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
		Map<String, String> dataMap = genTableService.previewCode(tableId);
		return AjaxResult.success(dataMap);
	}

	/**
	 * 生成代码（下载方式）
	 */
	@PreAuthorize(hasPermi="tool:gen:code")
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/download/{tableId}")
	public void download(HttpServletResponse response, @PathVariable("tableId") Long tableId) throws IOException {
		byte[] data = genTableService.downloadCode(tableId);
		genCode(response, data);
	}

	/**
	 * 生成代码（自定义路径）
	 */
	@PreAuthorize(hasPermi="tool:gen:code")
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/genCode/{tableId}")
	public AjaxResult genCode(@PathVariable("tableId") Long tableId) {
		genTableService.generatorCode(tableId);
		return AjaxResult.success();
	}

	/**
	 * 同步数据库
	 */
	@PreAuthorize(hasPermi="tool:gen:edit")
	@Log(title = "代码生成", businessType = BusinessType.UPDATE)
	@GetMapping("/synchDb/{tableId}")
	public AjaxResult synchDb(@PathVariable("tableId") Long tableId) {
		genTableService.synchDb(tableId);
		return AjaxResult.success();
	}

	/**
	 * 批量生成代码
	 */
	@PreAuthorize(hasPermi="tool:gen:code")
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/batchGenCode")
	public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
		Long[] tableIds = Convert.toLongArray(tables);
		byte[] data = genTableService.downloadCode(tableIds);
		genCode(response, data);
	}

	/**
	 * 生成zip文件
	 */
	private void genCode(HttpServletResponse response, byte[] data) throws IOException {
		response.reset();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		response.setHeader("Content-Disposition", "attachment; filename=\"lmfcode.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}
}