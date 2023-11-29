package com.first.shop.dto;

public class TempOrderProduct {
	// 주문 생성하는데 필요한 상품정보
	private int temp_id;
	private int product_id;
	private int quantity;
	private String product_name;
	private int discount;
	private int price;
	private int saleprice;
	private int totalprice;
	private int savepoint;
	private int totalsavepoint;
	private String product_image;
	private String product_thumbimage;
	
	// 총 합계 포인트, 합산 가격 생성 메서드
	public void setTotal() {
		this.saleprice = (int)(price - (price * (discount/100.0)));
		this.totalprice = saleprice * quantity;
		this.totalsavepoint = savepoint * quantity;
	}
		
	public int getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(int temp_id) {
		this.temp_id = temp_id;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getTotalsavepoint() {
		return totalsavepoint;
	}
	public void setTotalsavepoint(int totalsavepoint) {
		this.totalsavepoint = totalsavepoint;
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSavepoint() {
		return savepoint;
	}
	public void setSavepoint(int savepoint) {
		this.savepoint = savepoint;
	}
	public int getSalePrice() {
		return saleprice;
	}
	public void setSalePrice(int salePrice) {
		this.saleprice = salePrice;
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
		return "TempOrderProduct [temp_id=" + temp_id + ", product_id=" + product_id + ", quantity=" + quantity
				+ ", product_name=" + product_name + ", discount=" + discount + ", price=" + price + ", saleprice="
				+ saleprice + ", totalprice=" + totalprice + ", savepoint=" + savepoint + ", totalsavepoint="
				+ totalsavepoint + ", product_image=" + product_image + ", product_thumbimage=" + product_thumbimage
				+ "]";
	}
	
	
	
	
}
