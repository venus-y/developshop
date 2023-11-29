package com.first.shop.dto;

import java.util.List;

public class TempOrderProducts {
	List<TempOrderProduct> orderProducts;

	public List<TempOrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<TempOrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "TempOrderProducts [orderProducts=" + orderProducts + "]";
	}
	
	
}
