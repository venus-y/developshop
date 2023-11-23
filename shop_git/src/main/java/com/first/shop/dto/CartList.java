package com.first.shop.dto;

import java.util.List;

public class CartList {
	List<Cart> cartList;

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	@Override
	public String toString() {
		return "CartList [cartList=" + cartList + "]";
	}
	
	
	
}
