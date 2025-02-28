package com.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.entity.UserMaster;

public class UserModel {

	String userId;

	String firstName;

	String lastName;

	String mobile;

	String address;

	public UserModel() {
		super();
	}

	public UserModel(UserMaster userMaster) {
		super();
		this.userId = userMaster.getUserId();
		this.firstName = userMaster.getFirstName();
		this.lastName = userMaster.getLastName();
		this.mobile = userMaster.getMobile();
		this.address = userMaster.getAddress();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDAO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile="
				+ mobile + ", address=" + address + "]";
	}

	@JsonIgnore
	public boolean isValid() {
		return null != this.userId && null != this.firstName && null != this.lastName && null != this.mobile
				&& null != this.address;
	}
}
