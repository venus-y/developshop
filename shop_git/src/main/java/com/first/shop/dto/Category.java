package com.first.shop.dto;

// 상품 등록 시 카테고리별로 분류해서 등록할 수 있어야 하기 때문에 생성
public class Category {
	private int tier_no;
	private String category_name;
	private int category_code;
	private int category_parent_code;
	
	public int getTier_no() {
		return tier_no;
	}
	public void setTier_no(int tier_no) {
		this.tier_no = tier_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}
	public int getCategory_parent_code() {
		return category_parent_code;
	}
	public void setCategory_parent_code(int category_parent_code) {
		this.category_parent_code = category_parent_code;
	}
	@Override
	public String toString() {
		return "Category [tier_no=" + tier_no + ", category_name=" + category_name + ", category_code=" + category_code
				+ ", category_parent_code=" + category_parent_code + "]";
	}
	
	
	
}
