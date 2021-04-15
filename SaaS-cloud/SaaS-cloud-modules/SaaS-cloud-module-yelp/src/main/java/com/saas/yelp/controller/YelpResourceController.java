package com.saas.yelp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.saas.common.enums.BusinessType;
import com.saas.common.es.AggSpecification;
import com.saas.common.es.DimAggSpecification;
import com.saas.common.es.IndAggSpecification;
import com.saas.common.security.annotation.PreAuthorize;
import com.saas.log.annotation.Log;
import com.saas.yelp.domain.YelpResource;
import com.saas.yelp.service.YelpResourceService;

@RestController
@RequestMapping("/resource")
public class YelpResourceController extends BaseController {
	@Autowired
	private YelpResourceService yelpResourceService;

	/**
	 * 获取菜单列表
	 */
	@PreAuthorize(hasPermi = "yelp:resource:list")
	@GetMapping("/list")
	public AjaxResult list(DataPageable pageable) {
		DataPage<YelpResource> page = yelpResourceService.getListPage(pageable);
		return AjaxResult.success(page);
	}

	/**
	 * 获取菜单列表
	 */
	@PreAuthorize(hasPermi = "yelp:resource:list")
	@GetMapping("/testagg")
	public AjaxResult testagg() {
		List<DimAggSpecification> aggSpecifications = new ArrayList<>();
		DimAggSpecification agg = new DimAggSpecification();
		agg.setFieldName("organization");
		agg.setFieldType(AggSpecification.FieldType.KEYWORD);
		aggSpecifications.add(agg);
		agg = new DimAggSpecification();
		agg.setFieldName("content");
		agg.setFieldType(AggSpecification.FieldType.KEYWORD);
		aggSpecifications.add(agg);

		List<IndAggSpecification> indSpecifications = new ArrayList<>();
		IndAggSpecification ind = new IndAggSpecification();
		ind.setFieldName("commentCount");
		ind.setIndicatorType(AggSpecification.IndicatorType.SUM);
		indSpecifications.add(ind);
		ind = new IndAggSpecification();
		ind.setFieldName("usedCount");
		ind.setIndicatorType(AggSpecification.IndicatorType.AVG);
		indSpecifications.add(ind);

		try {
			return AjaxResult
					.success(yelpResourceService.aggregation(aggSpecifications, indSpecifications, null, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AjaxResult.success(0);
	}

	/**
	 * 获取详细信息
	 */
	@PreAuthorize(hasPermi = "yelp:resource:query")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") String id) {
		return AjaxResult.success(yelpResourceService.getById(id));
	}

	/**
	 * 新增
	 */
	@PreAuthorize(hasPermi = "yelp:resource:add")
	@Log(title = "点评资源", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody YelpResource model) {
		yelpResourceService.save(model);
		return toAjax(1);
	}

	/**
	 * 修改
	 */
	@PreAuthorize(hasPermi = "yelp:resource:edit")
	@Log(title = "点评资源", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody YelpResource model) {
		yelpResourceService.update(model);
		return toAjax(1);
	}

	/**
	 * 删除
	 */
	@PreAuthorize(hasPermi = "yelp:resource:remove")
	@Log(title = "点评资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable String[] ids) {
		for (String id : ids) {
			yelpResourceService.delete(id);
		}
		return toAjax(ids.length);
	}
}
