package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Orders;

public interface OrderDAO extends JpaRepository<Orders, String>{

}
