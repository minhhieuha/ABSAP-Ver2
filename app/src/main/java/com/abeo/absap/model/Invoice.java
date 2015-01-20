package com.abeo.absap.model;

import java.util.Date;



public class Invoice {

	private String id;
	private String invoiceNo;
	private Date invoiceDate;
	private double amount;
	private String status;
	private boolean isChecked = false;

	public Invoice(String id, String invoiceNo, Date invoiceDate,
			double amount, String status) {
		this.id = id;
		this.setInvoiceNo(invoiceNo);
		this.setInvoiceDate(invoiceDate);
		this.setAmount(amount);
		this.setStatus(status);
	}
	public Invoice(String id, String invoiceNo, Date invoiceDate,
			double amount) {
		this.id = id;
		this.setInvoiceNo(invoiceNo);
		this.setInvoiceDate(invoiceDate);
		this.setAmount(amount);
	}
	public Invoice(String id, String invoiceNo, Date invoiceDate,
			double amount,boolean isChecked) {
		super();
		this.id = id;
		this.setInvoiceNo(invoiceNo);
		this.setInvoiceDate(invoiceDate);
		this.setAmount(amount);
		this.isChecked = isChecked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
