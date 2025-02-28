package com.order.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String PUSH_SUCCESS = "Message successfully pushed";

	public static final String PUSH_FAIL = "Message push failed";
	
	public static final String NULL = "NULL";

	public static final String MISMATCH = "MISMATCH";
	
	public static final Map<String, String> ERR_MAP = new HashMap<>();
	
	public static final Map<String, String> STATUS_MAP = new HashMap<>();
	
	public static final String REC = "REC";
	
	public static final String IP = "IP";
	
	public static final String P = "P";

	public static final String INVALID_ID = "INVALID_ID";

	public static final String NO_DATA = "NO_DATA";

	public static final String SYS = "SYS";

	public static final String NOT_FOUND = "NOT_FOUND";
	
	public static final String NO_DATA_FOUND = "NO_DATA_FOUND";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final String INVALID_USER = "INVALID_USER";
	
	static {
		ERR_MAP.put(MISMATCH, "Items Mismatch");
		ERR_MAP.put(INVALID_ID, "Invalid Order Id");
		ERR_MAP.put(NO_DATA, "No Data Posted, Order Creation failed");
		ERR_MAP.put(SYS, "System failure");
		ERR_MAP.put(NOT_FOUND, "Order not found");
		ERR_MAP.put(NO_DATA_FOUND, "No Orders Found");
		ERR_MAP.put(INVALID_USER, "Invalid user");
		
		STATUS_MAP.put(REC, "Pending");
		STATUS_MAP.put(IP, "Processing");
		STATUS_MAP.put(P, "Completed");
	}	
}
