package com.wsun.seap.domain.po.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/17.
 */
public class Menu extends BaseEntity {

	private Menu parent;    // 父级菜单

	private String parentIds; // 所有父级编号

	private String name;    // 名称

	private String href;    // 链接

	private String target;    // 目标（ mainFrame、_blank、_self、_parent、_top）

	private String icon;    // 图标

	private Integer sort;    // 排序

	private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）

	private String isActiviti;    // 是否同步到工作流（1：同步；0：不同步）

	private String permission; // 权限标识

	private List<Menu> childList = new ArrayList<Menu>();// 拥有子菜单列表

}
