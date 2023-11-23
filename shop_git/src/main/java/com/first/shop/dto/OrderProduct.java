package com.first.shop.dto;

public class OrderProduct {
	private int order_product_id;
	private String order_id;
	private int product_id;
	private int quantity;
	private int price;
	private int savepoint;
	
	public int getSavepoint() {
		return savepoint;
	}
	public void setSavepoint(int savepoint) {
		this.savepoint = savepoint;
	}
	public int getOrder_product_id() {
		return order_product_id;
	}
	public void setOrder_product_id(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderProduct [order_product_id=" + order_product_id + ", order_id=" + order_id + ", product_id="
				+ product_id + ", quantity=" + quantity + ", price=" + price + ", savepoint=" + savepoint + "]";
	}
	
	
}
