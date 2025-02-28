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
import com.order.model.UserModel;
import com.order.service.UserService;
import com.order.util.Constants;
import com.order.util.ResponseUtility;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseUtility responseUtility;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
		LOGGER.info("Entered createUser at {}", System.currentTimeMillis());
		try {
			if (!userModel.isValid()) {
				throw new OrderException(Constants.INVALID_USER_DATA);
			}
			return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.OK);
		} catch (OrderException oe) {
			LOGGER.debug("OrderException in createUser {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in createUser {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting createUser at {}", System.currentTimeMillis());
		}
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers() {
		LOGGER.info("Entered getAllUsers at {}", System.currentTimeMillis());
		try {
			List<UserModel> userList = userService.getAllUsers();
			if(null == userList) {
				throw new OrderException(Constants.NO_USERS_FOUND);
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (OrderException oe) {
			LOGGER.debug("OrderException in getAllUsers {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in getAllUsers {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting getAllUsers at {}", System.currentTimeMillis());
		}
	}
}
