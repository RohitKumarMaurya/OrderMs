package com.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.entity.ItemMaster;

public class ItemModel {

	String itemId;

	String itemName;

	double itemRate;

	int itemQty;

	public ItemModel() {
		super();
	}

	public ItemModel(String itemId, String itemName, double itemRate, int itemQty) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemRate = itemRate;
		this.itemQty = itemQty;
	}
	
	public ItemModel(ItemMaster itemMaster) {
		super();
		this.itemId = itemMaster.getItemId();
		this.itemName = itemMaster.getitemName();
		this.itemRate = itemMaster.getItemRate();
		this.itemQty = itemMaster.getItemQty();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getitemName() {
		return itemName;
	}

	public void setitemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemRate() {
		return itemRate;
	}

	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	@Override
	public String toString() {
		return "ItemDAO [itemId=" + itemId + ", itemName=" + itemName + ", itemRate=" + itemRate + ", itemQty="
				+ itemQty + "]";
	}

	@JsonIgnore
	public boolean isValid() {
		return null != this.itemId && null != this.itemName && 0 != this.itemRate && 0 != this.itemQty;
	}
}
