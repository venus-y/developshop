package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
