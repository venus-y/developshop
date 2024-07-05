package com.first.shop.dto;

import java.util.Date;

public class Purchase_History {
	private String order_id;
	private Date order_date;
	private String status;
	private String payment_method;
	private int product_id;
	private int price;
	private int quantity;
	private int savepoint;
	private String product_name;
	private String product_thumbimage;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSavepoint() {
		return savepoint;
	}
	public void setSavepoint(int savepoint) {
		this.savepoint = savepoint;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_thumbimage() {
		return product_thumbimage;
	}
	public void setProduct_thumbimage(String product_thumbimage) {
		this.product_thumbimage = product_thumbimage;
	}
	@Override
	public String toString() {
		return "Purchase_History [order_id=" + order_id + ", order_date=" + order_date + ", status=" + status
				+ ", payment_method=" + payment_method + ", product_id=" + product_id + ", price=" + price
				+ ", quantity=" + quantity + ", savepoint=" + savepoint + ", product_name=" + product_name
				+ ", product_thumbimage=" + product_thumbimage + "]";
	}
	
	
	
}
