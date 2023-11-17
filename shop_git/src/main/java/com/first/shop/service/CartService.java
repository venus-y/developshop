package com.first.shop.service;

import java.util.List;

import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;

public interface CartService {
	int registerCart(Cart cart);
	
	Product CartProductImage(int product_id);
	
	List<Cart> getCartList(String user_id);
}
