package com.first.shop.dto;

import java.util.List;

public class OrderProductandCartList {
	
	List<OrderProduct> orderProductList;
	
	List<Cart> cartList;
	
	String tid;
	
	boolean cartCheck;
	
	int usedPoint;
	
	String delivery_address;
	
	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	int total_amount;
	
	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public boolean isCartCheck() {
		return cartCheck;
	}

	public void setCartCheck(boolean cartCheck) {
		this.cartCheck = cartCheck;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	@Override
	public String toString() {
		return "OrderProductandCartList [orderProductList=" + orderProductList + ", cartList=" + cartList + ", tid="
				+ tid + ", cartCheck=" + cartCheck + ", usedPoint=" + usedPoint + ", delivery_address="
				+ delivery_address + ", total_amount=" + total_amount + "]";
	}

	
	
	
	
}
