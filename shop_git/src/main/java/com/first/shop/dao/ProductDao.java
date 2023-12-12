package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.BrandSearchCondition;
import com.first.shop.dto.Category;
import com.first.shop.dto.Product;

public interface ProductDao {
	List<Product> list();
	
	int count();
	
	List<Product> pageList(Map map);
	
	Product productInfo(int product_id);
	
	List<Product> categorySet(Map map);
	
	int categoryCount(int category_id);
	
	List<Category> sortBrand();
	
	int getBrand_Count(Integer category_code);
	
	List<Product> getBrand(Map map);
	
	int getBrand_SearchCount(BrandSearchCondition brandSearchCondition);
	
	List<Product> getBrand_Search(BrandSearchCondition brandSearchCondition);
}
