package com.saas.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型常量
 * @author bruce
 *
 */
public class DomainConstants {

	public static final int DOMAIN_DEL_FLAG_DELETED = 1;
	public static final int DOMAIN_DEL_FLAG_NOT_DELETED = 0;
	public static final Map<Integer, String> DomainDelFlag = new HashMap<>();
	static {
		DomainDelFlag.put(0, "未删除");
		DomainDelFlag.put(1, "已删除");
	}

	public static final int DOMAIN__STATUS_OFFLINE = -1;
	public static final int DOMAIN__STATUS_AUDIT = 0;
	public static final int DOMAIN__STATUS_PUBLISH = 1;
	public static final Map<Integer, String> DomainStatus = new HashMap<>();
	static {
		DomainStatus.put(-1, "下线");
		DomainStatus.put(0, "待审");
		DomainStatus.put(1, "商用");
	}

}
