package com.first.shop.dto;

public class Cart {
	private int cart_id;
	private String user_id;
	private int product_id;
	private int quantity;
	private String product_name;
	private int savepoint;
	private int totalsavepoint;
	private int discount;
	private int price;
	private int saleprice;
	private int totalprice;
	private String product_image;
	private String product_thumbimage;
	
	
	public Cart() {
		// 장바구니가 생성될 때 자동으로 값을 생성
		init();
	}
	
	// 총 적립포인트와 총 상품가격을 정한다.
	public void init() {
		// 총 수량 * 적립포인트를 총 적립포인트로 한다.
		totalsavepoint = savepoint * quantity;
		// 총 상품 가격은 세일가격 * 수량으로 한다.
		// 세일가격은 정가 - ( 정가 * (할인률 / 100)) 으로 한다.
		saleprice = (int)(price - ( price * (discount / 100.0)));
		totalprice = saleprice * quantity;
		
	}
	
	
	public int getSavepoint() {
		return savepoint;
	}
	public void setSavepoint(int savepoint) {
		this.savepoint = savepoint;
	}
	public int getTotalsavepoint() {
		return totalsavepoint;
	}
	public void setTotalsavepoint(int totalsavepoint) {
		this.totalsavepoint = totalsavepoint;
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
	public int getSalePrice() {
		return saleprice;
	}
	public void setSalePrice(int salePrice) {
		this.saleprice = salePrice;
	}
	public int getTotalPrice() {
		return totalprice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalprice = totalPrice;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", user_id=" + user_id + ", product_id=" + product_id + ", quantity="
				+ quantity + ", product_name=" + product_name + ", savepoint=" + savepoint + ", totalsavepoint="
				+ totalsavepoint + ", discount=" + discount + ", price=" + price + ", saleprice=" + saleprice
				+ ", totalprice=" + totalprice + ", product_image=" + product_image + ", product_thumbimage="
				+ product_thumbimage + "]";
	}
	
	
}
