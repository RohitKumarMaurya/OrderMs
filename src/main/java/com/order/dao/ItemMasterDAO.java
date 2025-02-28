package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.ItemMaster;

public interface ItemMasterDAO extends JpaRepository<ItemMaster, String>{

}
