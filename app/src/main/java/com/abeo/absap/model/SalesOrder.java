package com.abeo.absap.model;

import java.io.Serializable;
import java.util.Date;

public class SalesOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String customerCode;
	private String customerName;
	private String status;
	private Date deliveryDate;

	public SalesOrder(String id, String customerCode, String customerName,
			String status, Date deliveryDate) {
		this.id = id;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.status = status;
		this.deliveryDate = deliveryDate;
	}

	public SalesOrder(String customerCode, String customerName, String status,
			Date deliveryDate) {
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.status = status;
		this.deliveryDate = deliveryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
