package com.first.shop.service;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.CartList;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.OrderProductandCartList;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.Purchase_History;
import com.first.shop.dto.User;

public interface OrderService {
	int registerOrders(int product_id, String id, int quantity);
	
	User getUserInfo(String user_id);
	
	int registerOrder(OrdersList list, OrderProductList list2, CartList cartList, int used_point, boolean cartcheck);

	int kakaopay_OrderRegister(OrderProductandCartList orderProductandCartList);

	List<Purchase_History> get_Purchase_History (Map map);
	
	int get_Purchase_Count (String user_id);
	
}

