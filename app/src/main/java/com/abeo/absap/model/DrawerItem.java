package com.abeo.absap.model;

public class DrawerItem {

	private String itemName;
	private int imgResID;
	private String title;
	private boolean istop;
	private boolean isCounterVisible = false;
	private String count = "0";

	public DrawerItem(String itemName, int imgResID) {
		this.itemName = itemName;
		this.imgResID = imgResID;
	}

	public DrawerItem(String itemName, int imgResID, boolean isCounterVisible,
			String count) {
		this.itemName = itemName;
		this.imgResID = imgResID;
		this.isCounterVisible = isCounterVisible;
		this.count = count;
	}

	public String getCount() {
		return this.count;
	}

	public boolean getCounterVisibility() {
		return this.isCounterVisible;
	}

	public void setCounterVisibility(boolean isCounterVisible) {
		this.isCounterVisible = isCounterVisible;
	}

	public DrawerItem(boolean istop) {
		this(null, 0);
		this.istop = istop;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public DrawerItem(String title) {
		this(null, 0);
		this.title = title;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isTop() {
		return istop;
	}
}
