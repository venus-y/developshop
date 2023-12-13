package com.first.shop.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewDaoImplTest {
	@Autowired
	ReviewDao reviewDao;
	
	// 댓글 등록
	@Test
	public void testReview() {
		Review review = new Review();
		review.setProduct_id(256);
		review.setUser_id("geumsung7769");
		review.setContent("입력테스트");
		review.setRating(3.5);
		
		int count = reviewDao.review(review);
		
		assertTrue(count == 1);
	}
	
	// 댓글 달 상품 정보 가져오기
	@Test
	public void testReviewProduct() {
		Product product = reviewDao.productInfo(255);
		System.out.println(product);
	}

}
