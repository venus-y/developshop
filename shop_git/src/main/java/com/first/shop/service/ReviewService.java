package com.first.shop.service;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

public interface ReviewService {
	 
	int writeReview(Review review);
	
	Product review_ProductInfo(int product_id);
}
