package com.springboot.authorize.domain.auth;

import java.util.List;

public class MenuVO {
	private String name;// 组件名
	private String path;// 路由地址
	private Boolean hidden;//
	private String redirect;
	private String component;
	private Boolean root;// 根节点

	private MenuMetaVO meta;

	private List<MenuVO> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public MenuMetaVO getMeta() {
		return meta;
	}

	public void setMeta(MenuMetaVO meta) {
		this.meta = meta;
	}

	public List<MenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

}
