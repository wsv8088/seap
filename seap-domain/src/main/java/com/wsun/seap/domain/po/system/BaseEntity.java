package com.wsun.seap.domain.po.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dbwangshuang on 2014/12/17.
 */
public class BaseEntity implements Serializable {
    private Long id;

    protected String remarks;    // 备注

    protected String createBy;    // 创建者

    protected Date createDate;// 创建日期

    protected String updateBy;    // 更新者

    protected Date updateDate;// 更新日期

    protected String yn; // 删除标记（0：正常；1：删除；2：审核）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }
}
