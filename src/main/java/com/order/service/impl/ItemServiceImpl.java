package com.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.ItemMasterDAO;
import com.order.entity.ItemMaster;
import com.order.model.ItemModel;
import com.order.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMasterDAO itemMasterDAO;

	@Override
	public ItemModel createItem(ItemModel itemModel) {
		ItemMaster itemMaster = new ItemMaster(itemModel);
		return new ItemModel(itemMasterDAO.save(itemMaster));
	}

	@Override
	public List<ItemModel> getAllItems() {
		List<ItemMaster> itemList = itemMasterDAO.findAll();
		if(null == itemList || itemList.isEmpty()) {
			return null;
		}
		return itemList.stream().map(item->new ItemModel(item)).collect(Collectors.toList());
	}

}
