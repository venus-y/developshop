package com.first.shop.service;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.BrandSearchCondition;
import com.first.shop.dto.Category;
import com.first.shop.dto.Product;

public interface ProductService {
	List<Product> getProductList();
	
	int countAllProduct();
	
	List<Product> getPageList(Map map);
	
	Product getProductInfo(int product_id);
	
	List<Product> getProduct_CategorySet(Map map);
	
	int getProduct_CategorySet_Count(int category_code);
	
	List<Category> sortByBrand();
	
	List<Product> getBrandPage(Map map);
	
	int getBrand_Product_Count(Integer category_code);
	
	int getBrandProduct_SearchCount(BrandSearchCondition brandSearchCondition);
	
	List<Product> getBrandProduct_Search(BrandSearchCondition brandSearchCondition);
}
