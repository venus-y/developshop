package com.first.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.first.shop.dao.ReviewDao;
import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewDao reviewDao;
	
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	
	// 리뷰 댓글 등록
	@Override
	public int writeReview(Review review) {
		return reviewDao.review(review);
	}

	// 댓글 작성하는데 필요한 상품정보 가져오기
	@Override
	public Product review_ProductInfo(int product_id) {
		return reviewDao.productInfo(product_id);
	}

	// 유저가 리뷰 작성한 적 있는지 체크
	@Override
	public int check_ReviewHistory(Review review) {
		return reviewDao.reviewHistory(review);		
	}

	// 리뷰 작성 전 해당 상품을 구매한 적이 있는지 체크
	@Override
	public int check_OrderHistory(Review review) {
		return reviewDao.orderHistory(review);
	}

	// 상품에 작성된 리뷰 개수 가져오기
	@Override
	public int getReviewCount(int product_id) {
		return reviewDao.reviewCount(product_id);
	}

	// 상품에 작성된 리뷰 목록 가져오기
	@Override
	public List<Review> getReviewList(Map map) {
		return reviewDao.reviewList(map);
	}

	// 상품페이지에 작성된 리뷰 수정
	@Override
	public int update_Review(Review review) {
		return reviewDao.update(review);
	}

}
