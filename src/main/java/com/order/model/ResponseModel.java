package com.order.model;

public class ResponseModel<T> {
	
	int status;
	
	T obj;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "ResponseDAO [status=" + status + ", obj=" + obj + "]";
	}
}
