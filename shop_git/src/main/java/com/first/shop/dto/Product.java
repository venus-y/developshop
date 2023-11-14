package com.first.shop.dto;

import java.util.Date;

public class Product {
	
	private int product_id;
	private String product_name;
	private int price;
	private int discount;
	private Date release_date;
	private int stock;
	private int category_code;
	private int savepoint;
	
	// 이미지 저장을 위한 변수 서비스단에서 상품 등록 처리시 1. 상품 등록 후 2. 등록된 상품의 상품번호를 참조한 상품이미지가 등록된다.
	private String product_image;
	private String product_thumbimage;
	
		
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getProduct_thumbimage() {
		return product_thumbimage;
	}
	public void setProduct_thumbimage(String product_thumbimage) {
		this.product_thumbimage = product_thumbimage;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}
	public int getSavepoint() {
		return savepoint;
	}
	public void setSavepoint(int savepoint) {
		this.savepoint = savepoint;
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", price=" + price
				+ ", discount=" + discount + ", release_date=" + release_date + ", stock=" + stock + ", category_code="
				+ category_code + ", savepoint=" + savepoint + ", product_image=" + product_image
				+ ", product_thumbimage=" + product_thumbimage + "]";
	}
	
	
}
