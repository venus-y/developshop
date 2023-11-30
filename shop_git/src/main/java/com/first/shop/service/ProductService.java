package com.first.shop.service;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.Product;

public interface ProductService {
	List<Product> getProductList();
	
	int countAllProduct();
	
	List<Product> getPageList(Map map);
	
	Product getProductInfo(int product_id);
	
	List<Product> getProduct_CategorySet(int category_code);
	
	int getProduct_CategorySet_Count(int category_code);
}