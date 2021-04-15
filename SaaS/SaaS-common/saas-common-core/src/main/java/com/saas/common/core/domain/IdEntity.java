package com.saas.common.core.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.saas.common.core.annotation.Excel;


@MappedSuperclass
public abstract class IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2900954483335531820L;
	
	@Excel(name = "编号")
	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

	@Override
	public int hashCode() {
		return id == null ? this.getClass().getName().hashCode() : this.getClass().getName().hashCode() + id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass() || null == this.id) {
			return false;
		}
		IdEntity key = (IdEntity) obj;
		return this.id.equals(key.getId());
	}
}
