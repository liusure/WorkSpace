package com.saas.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.common.core.entity.AjaxResult;
import com.saas.common.security.annotation.PreAuthorize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

/**
 * 缓存监控
 * 
 */
@ApiModel(value="缓存监控",description="缓存监控数据展示" )
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@ApiOperation(value = "获取缓存信息", notes = "返回数据格式", httpMethod = "GET")	
	@PreAuthorize("@ss.hasPermi('monitor:cache:list')")
	@GetMapping()
	public AjaxResult getInfo() throws Exception {
		Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
		Properties commandStats = (Properties) redisTemplate
				.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
		Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

		Map<String, Object> result = new HashMap<>(3);
		result.put("info", info);
		result.put("dbSize", dbSize);

		List<Map<String, String>> pieList = new ArrayList<>();
		commandStats.stringPropertyNames().forEach(key -> {
			Map<String, String> data = new HashMap<>(2);
			String property = commandStats.getProperty(key);
			data.put("name", StringUtils.removeStart(key, "cmdstat_"));
			data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
			pieList.add(data);
		});
		result.put("commandStats", pieList);
		return AjaxResult.success(result);
	}
}
