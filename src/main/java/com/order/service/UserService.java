package com.order.service;

import java.util.List;

import com.order.model.UserModel;

public interface UserService {

	UserModel createUser(UserModel userModel);

	List<UserModel> getAllUsers();

}
