package com.saas.common.es;

public class ESSaasEntity extends ESBaseEntity {

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
