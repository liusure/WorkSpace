/**
 * 
 */
package com.saas.yelp.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.saas.common.es.ESSaasEntity;

/**
 * @author 点评对象
 *
 */
public class YelpResource extends ESSaasEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String content;

	private String organization;

	private String subTitle;

	private String keywords;

	private String categorys;

	private String tags;

	/**
	 * 评论数
	 */
	private int commentCount;

	/**
	 * 想看人数
	 */
	private int likeCount;
	/**
	 * 看过人数
	 */
	private int usedCount;
	/**
	 * 打分人数
	 */
	private int scoreCount;

	/**
	 * 评分
	 */
	private double score;

	private String coverPicUrl;
	/**
	 * 多个用空格分开
	 */
	private String picUrls;

	private String videoUrl;

	private boolean recommendFlag = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}

	public int getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public boolean isRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(boolean recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	@JSONField(serialize = false)
	public Date getCreateTime() {
		return new Date(this.getCreateTimeMills());
	}

	@JSONField(serialize = false)
	public Date getUpdateTime() {
		return new Date(this.getUpdateTimeMills());
	}

}
