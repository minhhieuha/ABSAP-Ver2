package com.abeo.absap.model;

import java.io.Serializable;
import java.util.Date;

public class MasterItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String itemCode;
	private String itemName;
	private String wareHouse;
	private double quantity;
	private double price;
	private Date deliveryDate;
	private double discount;
	private double tax;
	private String itemsPerUnit;
	private int stockCount;

	public MasterItem(String itemCode, String itemName, String wareHouse,
			double quantity, double price) {

		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;
		this.quantity = quantity;
		this.price = price;

	}

	public MasterItem(String id, String itemCode, String itemName,
			String wareHouse, double quantity, double price) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;
		this.quantity = quantity;
		this.price = price;

	}
	public MasterItem(String id, String itemCode, String itemName,
			String wareHouse, double quantity) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;
		this.quantity = quantity;

	}
	public MasterItem(String id, String itemCode, String itemName,
			String wareHouse) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;

	}
	public MasterItem(String id, String itemCode, String itemName,
			String wareHouse,int stockCount) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;
		this.stockCount = stockCount;

	}
	public MasterItem(String id, String itemCode, String itemName,
			String wareHouse,int quantity,double price) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.wareHouse = wareHouse;
		this.quantity = quantity;
		this.price = price;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getItemsPerUnit() {
		return itemsPerUnit;
	}

	public void setItemsPerUnit(String itemsPerUnit) {
		this.itemsPerUnit = itemsPerUnit;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
}
