package com.first.shop.dto;

import java.util.List;

public class OrdersList {
	List<Orders> ordersList ;

	public List<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

	@Override
	public String toString() {
		return "OrdersList [ordersList=" + ordersList + "]";
	}
	
	
}
