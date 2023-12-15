package com.first.shop.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Review {
	private int review_id;
	private int product_id;
	private String user_id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date regdate;
	private String content;
	private double rating;
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", product_id=" + product_id + ", user_id=" + user_id + ", regdate="
				+ regdate + ", content=" + content + ", rating=" + rating + "]";
	}
	
	
	
	
	
}
