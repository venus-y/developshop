package com.first.shop.dto;


public class ProductImage {
	private int product_no;
	private String product_image;
	private String product_thumbimage;
	
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
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
	@Override
	public String toString() {
		return "ProductImage [product_no=" + product_no + ", product_image=" + product_image + ", product_thumbimage="
				+ product_thumbimage + "]";
	}
	
}
