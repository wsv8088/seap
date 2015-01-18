package com.wsun.seap.domain.po.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/17.
 */
public class Res extends BaseEntity {

    private Integer parentId;    // 父级菜单

    private Integer resType; // 资源类型

    private String name;    // 名称

    private String href;    // 链接

    private String target;    // 目标（ mainFrame、_blank、_self、_parent、_top）

    private String callback;    // 菜单加载到主区域后的回调函数

    private String icon;    // 图标

    private Integer sort;    // 排序

    private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）

    private Integer isLeaf; // 是否是叶子节点

    private String isSyncWorkflow;    // 是否同步到工作流（1：同步；0：不同步）

    private String permission; // 权限标识

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIsSyncWorkflow() {
        return isSyncWorkflow;
    }

    public void setIsSyncWorkflow(String isSyncWorkflow) {
        this.isSyncWorkflow = isSyncWorkflow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}


