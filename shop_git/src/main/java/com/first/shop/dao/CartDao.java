package com.first.shop.dao;

import java.util.List;

import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;

public interface CartDao {
	int register(Cart cart);
	
	Product productImage(int product_id);
	
	List<Cart> list(String user_id);
	
	int update(Cart cart);
	
	Cart cartInfo(int cart_id);
}
