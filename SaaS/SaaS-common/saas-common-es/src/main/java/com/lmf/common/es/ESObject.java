package com.saas.common.es;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ESObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		ESObject key = (ESObject) obj;
		return this.id.equals(key.getId());
	}
}
