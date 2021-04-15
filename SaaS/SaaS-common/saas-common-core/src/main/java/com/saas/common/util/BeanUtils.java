package com.saas.common.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils extends org.springframework.beans.BeanUtils {

	public static void copyProperties(Object source, Object target, boolean ignoreNullProperties) {
		if (ignoreNullProperties) {
			copyProperties(source, target, getNullPropertyNames(source));
		} else {
			copyProperties(source, target);
		}
	}

	/**
	 * 获取所有字段为null的属性名 用于BeanUtils.copyProperties()拷贝属性时，忽略空值
	 * 
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
