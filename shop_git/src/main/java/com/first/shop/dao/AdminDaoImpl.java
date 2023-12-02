package com.first.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Category;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.ProductImage;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	// 사용하기 위한 매퍼 설정
	String namespace= "com.first.shop.dao.AdminMapper.";
	
	// DB 접근 위한 객체
	@Autowired
	SqlSession session;
	
	// 상품 등록
	@Override
	public int register(Product product) {
		return session.insert(namespace+"register_product", product);
	}
	
	// 카테고리 불러오기
	@Override
	public List<Category> list() {
		return session.selectList(namespace+"get_categorylist");
	}
	
	// 이미지 등록
	@Override
	public int registerImg(ProductImage image) {
		return session.insert(namespace+"register_image", image);
	}
	
	// 상품 정보 수정
	@Override
	public int update(Product product) {
		return session.update(namespace+"update_product", product);
	}

	// 상품 삭제
	@Override
	public int delete(int product_id) {
		return session.delete(namespace+"delete_product", product_id);
	}
	
	// 주문 정보 가져오기
	@Override
	public Orders orderInfo(Orders orders) {
		return session.selectOne(namespace+"get_orderinfo", orders);
	}
	
	// 주문 정보 목록 가져오기
	@Override
	public List<Orders> orderInfoList() {
		return session.selectList(namespace+"get_orderinfolist");
	}
	
	// 배송 정보 등록
	@Override
	public int deliveryInfo(DeliveryInfo deliveryInfo) {
		return session.insert(namespace+"register_delivery_info", deliveryInfo);
	}

	

}
