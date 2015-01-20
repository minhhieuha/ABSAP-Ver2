package com.abeo.absap.model;

public class User {
	private int drawableResID;
	private String name;
	private String userName;

	public User(int drawableResID, String name, String username) {
		super();
		this.drawableResID = drawableResID;
		this.name = name;
		this.userName = username;
	}

	public int getDrawableResID() {
		return drawableResID;
	}

	public void setDrawableResID(int drawableResID) {
		this.drawableResID = drawableResID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}
}
