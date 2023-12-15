package com.first.shop.dao;

import java.util.List;

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
	
	// 유저가 상품에 리뷰를 작성한 적이 있는지 확인
	@Override
	public int reviewHistory(Review review) {
		return session.selectOne(namespace+"check_ReviewHistory", review);
	}
	
	// 유저가 해당상품 구매이력이 있는지 확인
	@Override
	public int orderHistory(Review review) {
		return session.selectOne(namespace+"check_OrderHistory", review);
	}

	// 해당 상품에 작성된 리뷰목록을 가져온다.
	@Override
	public List<Review> reviewList(int product_id) {
		return session.selectList(namespace+"get_ReviewList", product_id);
	}

}