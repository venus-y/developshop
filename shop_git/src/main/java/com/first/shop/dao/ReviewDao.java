package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

public interface ReviewDao {
	int review(Review review);
	
	Product productInfo(int product_id);
	
	int orderHistory(Review review);
	
	int reviewHistory(Review review);
	
//	List<Review> reviewList(int product_id);
	
	List<Review> reviewList(Map map);
	
	int reviewCount(int product_id);
	
	int update(Review review);
}
