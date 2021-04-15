/**
 * 
 */
package com.saas.uc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.saas.common.core.domain.IdEntity;

/**
 * 用户渠道等相关信息
 * 
 * @author bruce
 *
 */
@Entity
@Table(name = "ft_user_account")
public class UserAccountImport extends IdEntity {

	// ----------------------- 分销信息-----------------

	private Long invite; // 邀请人id 上级分校人ID

	private Integer sceneNo; // 场景编号

	private boolean lockInvite = false; // 是否锁定上级分销ID 0未锁定 1 锁定

	private Integer userRank = 0; // 会员等级

	private String channelCode; // 渠道码
	
	public Long getInvite() {
		return invite;
	}

	public void setInvite(Long invite) {
		this.invite = invite;
	}

	public Integer getSceneNo() {
		return sceneNo;
	}

	public void setSceneNo(Integer sceneNo) {
		this.sceneNo = sceneNo;
	}

	public boolean isLockInvite() {
		return lockInvite;
	}

	public void setLockInvite(boolean lockInvite) {
		this.lockInvite = lockInvite;
	}

	public Integer getUserRank() {
		return userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}
