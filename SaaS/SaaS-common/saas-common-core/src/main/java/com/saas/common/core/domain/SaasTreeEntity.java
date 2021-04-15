package com.saas.common.core.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SaasTreeEntity extends TreeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Saas账户ID
	 */
	private Long saasId;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
