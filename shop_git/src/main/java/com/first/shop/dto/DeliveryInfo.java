package com.first.shop.dto;

import java.util.Date;

public class DeliveryInfo {
	private int shipping_id;
	private String order_id;
	private Date shipping_date;
	private String shipping_address;
	private String tracking_number;
	private int company_id;
	private String company_name;
	
	public DeliveryInfo() {
		
	}
	
	public int getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(int shipping_id) {
		this.shipping_id = shipping_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Date getShipping_date() {
		return shipping_date;
	}
	public void setShipping_date(Date shipping_date) {
		this.shipping_date = shipping_date;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
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
	@Override
	public String toString() {
		return "DeliveryInfo [shipping_id=" + shipping_id + ", order_id=" + order_id + ", shipping_date="
				+ shipping_date + ", shipping_address=" + shipping_address + ", tracking_number=" + tracking_number
				+ ", company_id=" + company_id + ", company_name=" + company_name + "]";
	}

	
	
	
	
}
