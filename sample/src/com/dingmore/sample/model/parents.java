package com.dingmore.sample.model;

public class parents {
	private int id;        //菜单分类id
	private String name;    //菜单分类名称
	public parents(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
