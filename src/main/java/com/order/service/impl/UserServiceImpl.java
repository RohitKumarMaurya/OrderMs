package com.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.UserDAO;
import com.order.entity.UserMaster;
import com.order.model.UserModel;
import com.order.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserModel createUser(UserModel userModel) {
		UserMaster userMaster = new UserMaster(userModel);
		return new UserModel(userDAO.save(userMaster));
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserMaster> userList = userDAO.findAll();
		if(null == userList || userList.isEmpty()) {
			return null;
		}
		return userList.stream().map(user->new UserModel(user)).collect(Collectors.toList());
	}

}
