package com.order.exception;

import com.order.util.Constants;

public class OrderException extends Exception{

	private static final long serialVersionUID = 1L;

	public OrderException() {
		super();
	}
	
	public OrderException(String string) {
		super(Constants.ERR_MAP.get(string));
	}
}
