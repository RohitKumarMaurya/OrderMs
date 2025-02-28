package com.order.entity;

import com.order.model.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_mstr")
public class UserMaster {

	@Id
	@Column(name="userid")
	String userId;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="mobile")
	String mobile;
	
	@Column(name="address")
	String address;

	public UserMaster() {
		super();
	}
	
	public UserMaster(UserModel userModel) {
		super();
		this.userId = userModel.getUserId();
		this.firstName = userModel.getFirstName();
		this.lastName = userModel.getLastName();
		this.mobile = userModel.getMobile();
		this.address = userModel.getAddress();
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
		return "UserMaster [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile="
				+ mobile + ", address=" + address + "]";
	}
}
