package com.first.shop.dto;

import java.util.List;

public class OrderProductList {
	List<OrderProduct> orderProductList;

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	@Override
	public String toString() {
		return "OrderProductList [orderProductList=" + orderProductList + "]";
	}
	
	
}
