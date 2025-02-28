package com.order.service;

import java.util.List;

import com.order.model.ItemModel;

public interface ItemService {

	ItemModel createItem(ItemModel itemModel);

	List<ItemModel> getAllItems();

}
