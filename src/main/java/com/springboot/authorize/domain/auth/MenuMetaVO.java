package com.springboot.authorize.domain.auth;

public class MenuMetaVO {
	private String title;
	private String icon;
	private Boolean noCache;

	public MenuMetaVO(){
		
	}
	
	public MenuMetaVO(String title, String icon, Boolean noCache) {
		this.title = title;
		this.icon = icon;
		this.noCache = noCache;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getNoCache() {
		return noCache;
	}

	public void setNoCache(Boolean noCache) {
		this.noCache = noCache;
	}
}
