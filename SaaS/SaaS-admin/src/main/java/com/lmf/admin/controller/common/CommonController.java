package com.saas.admin.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saas.common.config.LmfConfig;
import com.saas.common.constant.Constants;
import com.saas.common.core.entity.AjaxResult;
import com.saas.common.util.ServletUtils;
import com.saas.common.util.file.FileUploadUtils;
import com.saas.common.util.file.FileUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 通用请求处理
 * 
 */
@ApiModel(value="通用请求处理",description="通用上传和下载请求" )
@RestController
public class CommonController {
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	

	/**
	 * 通用下载请求
	 * 
	 * @param fileName 文件名称
	 * @param delete   是否删除
	 */
	@ApiOperation(value = "通用下载请求", notes = "文件下载，delete为true时，下载完文件后删除服务器上的文件", httpMethod = "GET")
	@GetMapping("common/download")
	public void fileDownload(@ApiParam(name="文件名称",value="字符串",required=true)String fileName,@ApiParam(name="是否删除",value="true/false",required=true) Boolean delete, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			if (!FileUtils.checkAllowDownload(fileName)) {
				throw new Exception(String.format("文件名称(%s)非法，不允许下载。 ", fileName));
			}
			String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
			String filePath = LmfConfig.getDownloadPath() + fileName;

			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			FileUtils.setAttachmentResponseHeader(response, realFileName);
			FileUtils.writeBytes(filePath, response.getOutputStream());
			if (delete) {
				FileUtils.deleteFile(filePath);
			}
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
	}

	/**
	 * 通用上传请求
	 */
	@ApiOperation(value = "通用上传请求", notes = "上传完文件，返回文件服务器上的地址", httpMethod = "POST")
	@PostMapping("/common/upload")
	public AjaxResult uploadFile(MultipartFile file) throws Exception {
		try {
			// 上传文件路径
			String filePath = LmfConfig.getUploadPath();
			// 上传并返回新文件名称
			String fileName = FileUploadUtils.upload(filePath, file);
			String url = ServletUtils.getUrl() + fileName;
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", fileName);
			ajax.put("url", url);
			return ajax;
		} catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	 * 本地资源通用下载
	 */
	@ApiOperation(value = "本地资源通用下载", notes = "下载本地资源文件", httpMethod = "GET")
	@GetMapping("/common/download/resource")
	public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			if (!FileUtils.checkAllowDownload(resource)) {
				throw new Exception(String.format("资源文件(%s)非法，不允许下载。 ", resource));
			}
			// 本地资源路径
			String localPath = LmfConfig.getProfile();
			// 数据库资源地址
			String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
			// 下载名称
			String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			FileUtils.setAttachmentResponseHeader(response, downloadName);
			FileUtils.writeBytes(downloadPath, response.getOutputStream());
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
	}
}
