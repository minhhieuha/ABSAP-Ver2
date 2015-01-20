package com.abeo.absap.model;


public class DisplayKPI {

	private String id;
	private String group;
	private String itemCode;
	private String itemName;
	private String fromPoint;
	private String toPoint;
	private String point;

	public DisplayKPI(String id, String group, String itemCode,
			String itemName, String fromPoint, String toPoint, String point) {
		this.id = id;
		this.group = group;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.fromPoint = fromPoint;
		this.toPoint = toPoint;
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getFromPoint() {
		return fromPoint;
	}

	public void setFromPoint(String fromPoint) {
		this.fromPoint = fromPoint;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getToPoint() {
		return toPoint;
	}

	public void setToPoint(String toPoint) {
		this.toPoint = toPoint;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
}
