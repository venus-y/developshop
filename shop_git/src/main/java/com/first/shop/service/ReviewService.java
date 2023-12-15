package com.first.shop.service;

import java.util.List;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

public interface ReviewService {
	 
	int writeReview(Review review);
	
	Product review_ProductInfo(int product_id);
	
	int check_ReviewHistory(Review review);
	
	int check_OrderHistory(Review review);
	
	List<Review> getReviewList(int product_id);
}
