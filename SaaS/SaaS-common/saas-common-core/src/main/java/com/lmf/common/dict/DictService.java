/**
 * 
 */
package com.saas.common.dict;

/**
 * @author bruce
 *
 */
public interface DictService {
	public String getDictLabel(String dictValue, String dictType);

	public String getDictValue(String dictLabel, String dictType);
}
