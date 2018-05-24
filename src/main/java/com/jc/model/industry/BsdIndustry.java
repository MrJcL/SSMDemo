package com.jc.model.industry;

import java.sql.Timestamp;

/**
 * 
 */
public class BsdIndustry implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String industryId;
	private String parentIndustryId;
	private String industryNo;
	private String industryName;
	private String industryDescription;
	private String recordRemark;
	private String recordStatus;
	private Timestamp createTime;
	private String createUserId;
	private Timestamp updateTime;
	private String updateUserId;
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getParentIndustryId() {
		return parentIndustryId;
	}
	public void setParentIndustryId(String parentIndustryId) {
		this.parentIndustryId = parentIndustryId;
	}
	public String getIndustryNo() {
		return industryNo;
	}
	public void setIndustryNo(String industryNo) {
		this.industryNo = industryNo;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIndustryDescription() {
		return industryDescription;
	}
	public void setIndustryDescription(String industryDescription) {
		this.industryDescription = industryDescription;
	}
	public String getRecordRemark() {
		return recordRemark;
	}
	public void setRecordRemark(String recordRemark) {
		this.recordRemark = recordRemark;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
}