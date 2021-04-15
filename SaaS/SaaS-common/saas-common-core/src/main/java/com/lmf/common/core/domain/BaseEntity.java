package com.saas.common.core.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saas.common.core.annotation.Excel;
import com.saas.common.core.annotation.Excel.ColumnType;

/**
 * Entity基类
 * 
 */
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4357255768627808088L;

	@Excel(name = "创建时间", dateFormat =  "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime = new Date();
	
	@Excel(name = "修改时间", dateFormat =  "yyyy-MM-dd HH:mm:ss")	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime = new Date();
	
	@Excel(name = "创建人ID")
	private Long createUserId;
	
	@Excel(name = "修改人ID")
	private Long updateUserId;
	
	@Excel(name = "创建人")
	private String createBy;
	
	@Excel(name = "修改人")
	private String updateBy;
	
	// 对象状态 -1 下线 0 待审 1 商用
	@Excel(name = "状态",readConverterExp = "-1=下线,0=待审,1=商用")
	private Integer statusFlag = 0;
	// 删除状态 0 未删除 1 已删除
	private Integer delFlag = 0;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
