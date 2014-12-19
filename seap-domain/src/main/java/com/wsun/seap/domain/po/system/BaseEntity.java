package com.wsun.seap.domain.po.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dbwangshuang on 2014/12/17.
 */
public class BaseEntity implements Serializable {
	protected String remarks;    // 备注

	protected User createBy;    // 创建者

	protected Date createDate;// 创建日期

	protected User updateBy;    // 更新者

	protected Date updateDate;// 更新日期

	protected String yn; // 删除标记（0：正常；1：删除；2：审核）

	public String getRemarks () {
		return remarks;
	}

	public void setRemarks (String remarks) {
		this.remarks = remarks;
	}

	public User getCreateBy () {
		return createBy;
	}

	public void setCreateBy (User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate () {
		return createDate;
	}

	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy () {
		return updateBy;
	}

	public void setUpdateBy (User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate () {
		return updateDate;
	}

	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getYn () {
		return yn;
	}

	public void setYn (String yn) {
		this.yn = yn;
	}
}
