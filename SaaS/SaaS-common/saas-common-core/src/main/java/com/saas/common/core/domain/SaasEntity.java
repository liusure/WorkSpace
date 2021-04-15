/**
 * 
 */
package com.saas.common.core.domain;

import javax.persistence.MappedSuperclass;

/**
 * Saas 模型基类
 * 
 * @author bruce
 *
 */
@MappedSuperclass
public class SaasEntity extends BaseEntity {
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
