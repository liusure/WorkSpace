package com.saas.system.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.saas.common.core.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 字典类型表
 * 
 */
@Entity
@Table(name = "sys_dict_type")
public class SysDictType extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 字典名称 */
	@Excel(name = "字典名称")
	private String dictName;

	/** 字典类型 */
	@Excel(name = "字典类型")
	private String dictType;
	
	@Excel(name = "备注")
	private String remark;

	@NotBlank(message = "字典名称不能为空")
	@Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
