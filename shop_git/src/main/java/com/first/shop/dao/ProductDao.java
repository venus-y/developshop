package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.Product;

public interface ProductDao {
	List<Product> list();
	
	int count();
	
	List<Product> pageList(Map map);
	
	Product productInfo(int product_id);
	
}
