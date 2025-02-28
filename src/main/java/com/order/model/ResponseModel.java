package com.order.model;

public class ResponseModel<T> {
	
	int status;
	
	T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDAO [status=" + status + ", data=" + data + "]";
	}
}
