package com.first.shop.dao;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

public interface ReviewDao {
	int review(Review review);
	
	Product productInfo(int product_id);
}
