package com.first.shop.service;

import com.first.shop.dto.User;

public interface OrderService {
	int registerOrders(int product_id, String id, int quantity);
	
	User getUserInfo(String user_id);

//	int updateUser(User user);
//	
//	int updateProduct(Product product);
}
