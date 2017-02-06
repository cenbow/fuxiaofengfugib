package com.cqliving.cloud.online.security.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
	
	private long id;
	private String iconCls;
	private long pid;
	private Byte showMode;
	private String titleFirstSpell;
	private String title;
	private String url;
	private List<MenuView> children = new ArrayList<MenuView>();
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Byte getShowMode() {
		return showMode;
	}


	public void setShowMode(Byte showMode) {
		this.showMode = showMode;
	}


	public String getTitleFirstSpell() {
		return titleFirstSpell;
	}


	public void setTitleFirstSpell(String titleFirstSpell) {
		this.titleFirstSpell = titleFirstSpell;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public long getPid() {
		return pid;
	}


	public void setPid(long pid) {
		this.pid = pid;
	}


	public MenuView() {
		super();
	}
	
	public MenuView(long id, String title, String iconCls,long pid) {
		super();
		this.id = id;
		this.iconCls = iconCls;
		this.pid = pid;
		this.title = title;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<MenuView> getChildren() {
		return children;
	}

	public void setChildren(List<MenuView> children) {
		this.children = children;
	}

}
