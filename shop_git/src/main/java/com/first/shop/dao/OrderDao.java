package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.Cart;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.Purchase_History;
import com.first.shop.dto.User;

public interface OrderDao {
	int register(Orders orders);
	
	int kakaopay_order(Orders orders);
	
	int kakaopay_orderproduct(OrderProduct orderProduct);
	
	int update(User user);
	
	int update(Product product);
	
	User user(String id);
	
	Product product(int product_id);

	int delete(Cart cart);

	int order_product(OrderProduct orderProduct);
	
	int delivery_info(DeliveryInfo deliveryInfo);
	
	List<Purchase_History> purchase_History(Map map);
	
	int purchase_Count(String user_id);
}
