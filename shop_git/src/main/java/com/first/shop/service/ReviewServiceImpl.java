package com.first.shop.service;

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

}
