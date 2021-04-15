package com.saas.system.domain;

import com.saas.common.core.domain.BaseEntity;
import com.saas.common.core.domain.SysUser;
import com.saas.common.core.domain.TreeEntity;


import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统目录对象 sys_category
 * 
 * @author bruce
 * @date 2021-01-07
 */
@Entity
@Table(name = "sys_category")
public class SysCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 名称 */
    private String name;

    public void setName(String name) 
    {
        this.name = name;
    }
    public String getName() 
    {
        return name;
    }
    
    
    public static void main(String[] args) {
		System.out.println(BaseEntity.class.isAssignableFrom(SysUser.class));
		System.out.println(BaseEntity.class.isAssignableFrom(SysCategory.class));
		boolean isExtendsBaseEntity = BaseEntity.class.isAssignableFrom(SysCategory.class);
		if(!isExtendsBaseEntity) {
			isExtendsBaseEntity = TreeEntity.class.isAssignableFrom(SysCategory.class);
		}		
		System.out.println(isExtendsBaseEntity);
	}
}
