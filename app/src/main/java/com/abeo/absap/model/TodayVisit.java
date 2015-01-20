package com.abeo.absap.model;

import java.io.Serializable;


public class TodayVisit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String address;
	private String status;

	public TodayVisit(String id, String code, String name, String address,String status) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.status = status;
	}
	public TodayVisit(String id, String code, String name, String address) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
