package com.order.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.exception.OrderException;
import com.order.model.ErrorModel;
import com.order.model.ItemModel;
import com.order.service.ItemService;
import com.order.util.Constants;
import com.order.util.ResponseUtility;

@RestController
@RequestMapping("/item")
public class ItemRest {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ResponseUtility responseUtility;

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemRest.class);

	@PostMapping("/create")
	public ResponseEntity<?> createItem(@RequestBody ItemModel itemModel) {
		LOGGER.info("Entered createItem at {}", System.currentTimeMillis());
		try {
			if (!itemModel.isValid()) {
				throw new OrderException(Constants.INVALID_ITEM_DATA);
			}
			return new ResponseEntity<>(itemService.createItem(itemModel), HttpStatus.OK);
		} catch (OrderException oe) {
			LOGGER.debug("OrderException in createItem {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in createItem {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting createItem at {}", System.currentTimeMillis());
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllItems() {
		LOGGER.info("Entered getAllItems at {}", System.currentTimeMillis());
		try {
			List<ItemModel> userList = itemService.getAllItems();
			if(null == userList) {
				throw new OrderException(Constants.NO_ITEMS_FOUND);
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (OrderException oe) {
			LOGGER.debug("OrderException in getAllItems {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in getAllItems {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting getAllItems at {}", System.currentTimeMillis());
		}
	}
}
