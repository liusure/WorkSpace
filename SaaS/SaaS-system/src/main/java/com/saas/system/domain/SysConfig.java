package com.saas.system.domain;

import com.saas.common.core.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 参数配置对象 sys_config
 * 
 * @author bruce
 * @date 2021-01-06
 */
@Entity
@Table(name = "sys_config")
public class SysConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 备注 */
    private String remark;

    /** 系统内置（Y是 N否） */
    private boolean configType;

    /** 参数键值 */
    private String configValue;

    /** 参数键名 */
    private String configKey;

    /** 参数名称 */
    private String configName;

    public void setRemark(String remark) 
    {
        this.remark = remark;
    }
    public String getRemark() 
    {
        return remark;
    }
    public void setConfigType(boolean configType) 
    {
        this.configType = configType;
    }
	public boolean isConfigType() 
    {
        return configType;
    }
    public void setConfigValue(String configValue) 
    {
        this.configValue = configValue;
    }
    public String getConfigValue() 
    {
        return configValue;
    }
    public void setConfigKey(String configKey) 
    {
        this.configKey = configKey;
    }
    public String getConfigKey() 
    {
        return configKey;
    }
    public void setConfigName(String configName) 
    {
        this.configName = configName;
    }
    public String getConfigName() 
    {
        return configName;
    }
}
