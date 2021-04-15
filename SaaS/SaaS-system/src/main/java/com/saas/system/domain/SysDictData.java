package com.saas.system.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.saas.common.core.annotation.Excel;
import com.saas.common.core.annotation.Excel.ColumnType;
import com.saas.common.core.domain.BaseEntity;

/**
 * 字典数据表
 * 
 */
@Entity
@Table(name = "sys_dict_data")
public class SysDictData extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 字典排序 */
	@Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
	private Long dictSort;

	/** 字典标签 */
	@Excel(name = "字典标签")
	private String dictLabel;

	/** 字典键值 */
	@Excel(name = "字典键值")
	private String dictValue;

	/** 字典类型 */
	@Excel(name = "字典类型")
	private String dictType;

	/** 样式属性（其他样式扩展） */
	private String cssClass;

	/** 表格字典样式 */
	private String listClass;
	
	@Excel(name = "备注")
	private String remark;

	/** 是否默认（Y是 N否） */
	@Excel(name = "是否默认", readConverterExp = "true=是,false=否")
	private boolean defaultFlag;

	public Long getDictSort() {
		return dictSort;
	}

	public void setDictSort(Long dictSort) {
		this.dictSort = dictSort;
	}

	@NotBlank(message = "字典标签不能为空")
	@Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
	public String getDictLabel() {
		return dictLabel;
	}

	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}

	@NotBlank(message = "字典键值不能为空")
	@Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	@Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getListClass() {
		return listClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}

	public boolean isDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
