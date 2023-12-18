package com.first.shop.service;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

public interface ReviewService {
	 
	int writeReview(Review review);
	
	Product review_ProductInfo(int product_id);
	
	int check_ReviewHistory(Review review);
	
	int check_OrderHistory(Review review);
	
//	List<Review> getReviewList(int product_id);
	
	int getReviewCount(int product_id);
	
	List<Review> getReviewList(Map map);
	
	int update_Review(Review review);
	
	Review get_ReviewInfo(Review review);
	
	int delete_Review(Review review);
}
