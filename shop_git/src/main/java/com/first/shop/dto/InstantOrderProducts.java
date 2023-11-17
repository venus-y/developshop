package com.first.shop.dto;

import java.util.List;

public class InstantOrderProducts {
	List<InstantOrderProduct> orderProducts;

	public List<InstantOrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<InstantOrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "InstantOrderProducts [orderProducts=" + orderProducts + "]";
	}
	
	
}
