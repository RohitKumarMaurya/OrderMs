package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.UserMaster;

public interface UserDAO extends JpaRepository<UserMaster, String>{

}
