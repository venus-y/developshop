package com.first.shop.dao;

import com.first.shop.dto.Cart;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.User;

public interface OrderDao {
	int register(Orders orders);
	
	int update(User user);
	
	int update(Product product);
	
	User user(String id);
	
	Product product(int product_id);

	int delete(Cart cart);

	int order_product(OrderProduct orderProduct);
	
	int delivery_info(DeliveryInfo deliveryInfo);
	
}
