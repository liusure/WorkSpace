package com.saas.yelp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.saas.common.es.ElasticsearchService;
import com.saas.yelp.domain.YelpResource;

@Component
public class YelpResourceService extends ElasticsearchService<YelpResource> {

	public static final String INDEX = "yelp_resource";
	protected final Logger logger = LoggerFactory.getLogger(YelpResourceService.class);

	@Override
	protected String getIndex() {
		return INDEX;
	}

}
