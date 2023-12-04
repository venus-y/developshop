package com.first.shop.dao;

import java.util.List;

import com.first.shop.dto.Category;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.ProductImage;

public interface AdminDao {
	
	int register(Product product);

	List<Category> list();
	
	int registerImg(ProductImage image);
	
	int update(Product product);
	
	int delete(int product_id);
	
	List<Orders> orderInfoList();
	
	Orders orderInfo(Orders orders);
	
	int deliveryInfo(DeliveryInfo deliveryInfo);
	
	int updateOrders(Orders orders);
}
