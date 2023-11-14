package com.first.shop.service;

import java.util.List;

import com.first.shop.dto.Category;
import com.first.shop.dto.Product;
import com.first.shop.dto.ProductImage;

public interface AdminService {
	
	int registerProduct(Product product);
	
	List<Category> getCategoryList();

	int registerProductImage(ProductImage image);
}
