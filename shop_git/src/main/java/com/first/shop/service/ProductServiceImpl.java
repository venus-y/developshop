package com.first.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.shop.dao.ProductDao;
import com.first.shop.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	// 상품 목록 조회
	@Override
	public List<Product> getProductList() {
		return productDao.list(); 
	}
	
	
	// 상품 개수 조회
	@Override
	public int countAllProduct() {
		return productDao.count();
	}
	
	// 상품 페이지 정보 가져오기
	@Override
	public List<Product> getPageList(Map map) {
		return productDao.pageList(map);
	}

	
	// 상품 정보 조회
	@Override
	public Product getProductInfo(int product_id) {
		Product productInfo = productDao.productInfo(product_id);	
		return productInfo;
	}

	
	// 카테코리 코드별로 상품정보를 가져온다.
	@Override
	public List<Product> getProduct_CategorySet(Map map) {
		List<Product> productList = productDao.categorySet(map);
		return productList;
	}

	// 카테고리로 분류된 상품들의 총 개수를 가져온다.
	@Override
	public int getProduct_CategorySet_Count(int category_code) {
		return productDao.categoryCount(category_code);
	}

}
