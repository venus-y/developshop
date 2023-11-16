package com.first.shop.dto;

public class DeliveryCompany {
	private int company_id;
	private String company_name;
	private String company_tel;
	private String description;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "DeliveryCompany [company_id=" + company_id + ", company_name=" + company_name + ", company_tel="
				+ company_tel + ", description=" + description + "]";
	}
	
	
}
