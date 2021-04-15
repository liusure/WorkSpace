package com.saas.common.es;

public class ESBaseEntity extends ESObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String createBy;
	private String updateBy;

	// 对象状态 -1 下线 0 待审 1 商用
	private Integer statusFlag = 0;

	private boolean delFlag = false;// 是否删除

	/**
	 * 创建时间
	 */
	private Long createTimeMills;

	private Long updateTimeMills;
	
	private Long createUserId;
	private Long updateUserId;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Long getCreateTimeMills() {
		return createTimeMills;
	}

	public void setCreateTimeMills(Long createTimeMills) {
		this.createTimeMills = createTimeMills;
	}

	public Long getUpdateTimeMills() {
		return updateTimeMills;
	}

	public void setUpdateTimeMills(Long updateTimeMills) {
		this.updateTimeMills = updateTimeMills;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

}
