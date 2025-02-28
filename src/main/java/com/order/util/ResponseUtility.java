package com.order.util;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.order.model.ErrorModel;
import com.order.model.ResponseModel;

@Component
public class ResponseUtility {
	
	private Gson gson = new Gson();

	public String setSuccessResponse(Object obj) {
		ResponseModel<Object> response = new ResponseModel<>();
		response.setStatus(1);
		response.setObj(obj);
		return gson.toJson(response);
	}

	public ErrorModel setFailureResponse(Exception e) {
		ErrorModel response = new ErrorModel();
		response.setStatus(0);
		response.setMessage(e.getMessage());
		return response;
	}

}
