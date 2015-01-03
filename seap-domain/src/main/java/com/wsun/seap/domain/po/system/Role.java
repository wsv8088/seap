package com.wsun.seap.domain.po.system;

/**
 * Created by dbwangshuang on 2014/12/17.
 */
public class Role extends BaseEntity {

    private Integer officeId;    // 所属机构

    private String name;    // 名称

    private String description;    // 描述

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
