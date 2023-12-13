package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	SqlSession session;
	
	String namespace = "com.first.shop.dao.ReviewDao.";
	
	// 리뷰 등록
	@Override
	public int review(Review review) {
		return session.insert(namespace+"write_Review",review);
	}
	
	// 리뷰 등록에 필요한 상품 정보
	@Override
	public Product productInfo(int product_id) {
		return session.selectOne(namespace+"review_ProductInfo", product_id);
	}

}
