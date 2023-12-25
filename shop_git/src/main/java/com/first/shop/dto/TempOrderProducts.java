package com.first.shop.dto;

import java.util.List;

public class TempOrderProducts {
	List<TempOrderProduct> orderProducts;
	
	// 장바구니에서 넘어온 요청인지 체크
	boolean cartCheck;
	
	public boolean isCartCheck() {
		return cartCheck;
	}

	public void setCartCheck(boolean cartCheck) {
		this.cartCheck = cartCheck;
	}

	public List<TempOrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<TempOrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "TempOrderProducts [orderProducts=" + orderProducts + ", cartCheck=" + cartCheck + "]";
	}
	
	
}
