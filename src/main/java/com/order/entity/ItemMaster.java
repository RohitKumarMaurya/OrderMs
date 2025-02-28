package com.order.entity;

import java.time.LocalDateTime;

import com.order.model.ItemModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_mstr")
public class ItemMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemid")
	String itemId;

	@Column(name = "item_name")
	String itemName;

	@Column(name = "item_rate")
	double itemRate;

	@Column(name = "item_qty")
	int itemQty;

	@Column(name = "inserttmstmp")
	LocalDateTime insertTimeStamp;

	@Column(name = "updatetmstmp")
	LocalDateTime updateTimeStamp;
	
	public ItemMaster() {
		super();
	}
	
	public ItemMaster(ItemModel itemModel) {
		super();
		this.itemName = itemModel.getitemName();
		this.itemRate = itemModel.getItemRate();
		this.itemQty = itemModel.getItemQty();
		this.insertTimeStamp = LocalDateTime.now();
		this.updateTimeStamp = LocalDateTime.now();
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

	public LocalDateTime getInsertTimeStamp() {
		return insertTimeStamp;
	}

	public void setInsertTimeStamp(LocalDateTime insertTimeStamp) {
		this.insertTimeStamp = insertTimeStamp;
	}

	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	@Override
	public String toString() {
		return "ItemMaster [itemId=" + itemId + ", itemName=" + itemName + ", itemRate=" + itemRate + ", itemQty="
				+ itemQty + ", insertTimeStamp=" + insertTimeStamp + ", updateTimeStamp=" + updateTimeStamp + "]";
	}
}
