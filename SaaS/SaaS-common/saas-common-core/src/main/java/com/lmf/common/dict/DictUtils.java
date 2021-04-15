package com.saas.common.dict;

import com.saas.common.util.SpringUtils;

public class DictUtils {

	public static String getDictLabel(String dictValue, String dictType, String separator) {
		DictService dictService = SpringUtils.getBean(DictService.class);
		String[] values = dictValue.split(separator);
		StringBuilder builder = new StringBuilder();
		for (String value : values) {
			builder.append(dictService.getDictLabel(value, dictType));
			builder.append(separator);
		}
		return builder.delete(builder.length() - separator.length(), builder.length()).toString();
	}

	public static String getDictValue(String dictLabel, String dictType, String separator) {
		DictService dictService = SpringUtils.getBean(DictService.class);
		String[] labels = dictLabel.split(separator);
		StringBuilder builder = new StringBuilder();
		for (String label : labels) {
			builder.append(dictService.getDictValue(label, dictType));
			builder.append(separator);
		}
		return builder.delete(builder.length() - separator.length(), builder.length()).toString();
	}

}
