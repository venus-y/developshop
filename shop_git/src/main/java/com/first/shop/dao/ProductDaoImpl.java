package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.BrandSearchCondition;
import com.first.shop.dto.Category;
import com.first.shop.dto.CategorySearchCondition;
import com.first.shop.dto.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	SqlSession session;
	
	String namespace = "com.first.shop.dao.ProductMapper.";
	
	// 상품목록 가져오기
	@Override
	public List<Product> list() {
		return session.selectList(namespace+"getProductList");
	}
	
	// 상품 총 개수 카운트
	@Override
	public int count() {
		return session.selectOne(namespace+"countAllProduct");
	}

	// 페이지 정보 반환
	@Override
	public List<Product> pageList(Map map) {
		return session.selectList(namespace+"getProductPage", map);
	}
	
	// 상품 정보 반환
	@Override
	public Product productInfo(int product_id) {
		return session.selectOne(namespace+"getProductInfo", product_id);
	}
	
	// 같은 카테고리에 속하는 상품들을 반환
	@Override
	public List<Product> categorySet(CategorySearchCondition csc) {
		return session.selectList(namespace+"getProduct_CategorySet", csc);
	}
	
	// 카테고리에 속한 상품들의 총 개수 반환
	@Override
	public int categoryCount(CategorySearchCondition csc) {
		return session.selectOne(namespace+"getProduct_CategorySet_Count", csc);

	}
	
	// 브랜드별 상품 분류
	@Override
	public List<Category> sortBrand() {
		return session.selectList(namespace+"sort_Brand");
	}
	
	// 브랜드 상품 페이지
	@Override
	public List<Product> getBrand(Map map) {
		return session.selectList(namespace+"get_Brand_Page", map);
	}
	
	// 브랜드 상품 총 개수 가져오기
	@Override
	public int getBrand_Count(Integer category_code) {
		return session.selectOne(namespace+"get_Brand_Product_Count", category_code);
	}
	
	// 브랜드 상품 검색 결과로 나온 총 상품 수량 가져오기
	@Override
	public int getBrand_SearchCount(BrandSearchCondition brandSearchCondition) {
		return session.selectOne(namespace+"get_Brand_Product_Search_Count", brandSearchCondition);
	}
	
	// 브랜드 상품 검색 결과로 나온 상품 목록 가져오기
	@Override
	public List<Product> getBrand_Search(BrandSearchCondition brandSearchCondition) {
		return session.selectList(namespace+"get_Brand_Product_Search", brandSearchCondition);
	}

	
	
	

}
