package com.first.shop.dao;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;
import com.first.shop.dto.ReviewPageHandler;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewDaoImplTest {
	@Autowired
	ReviewDao reviewDao;
	
	// 리뷰 등록
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
	
	// 리뷰 달 상품 정보 가져오기
	@Test
	public void testReviewProduct() {
		Product product = reviewDao.productInfo(255);
		System.out.println(product);
	}
	
	// 상품에 리뷰 등록한 적이 있는지 확인
	@Test
	public void checkReviewHistory() {
		Review review = new Review();
		review.setUser_id("geumsung7769");
		review.setProduct_id(255);
		
		int check = reviewDao.reviewHistory(review);
		
		System.out.println("체크결과: " + check);		
	}
	
	// 리뷰 작성 전 상품을 주문한 적이 있는지 확인
	@Test
	public void checkOrderHistory() {
		Review review = new Review();
		review.setUser_id("geumsung7769");
		review.setProduct_id(255);
		
		int count = reviewDao.orderHistory(review);
		
		System.out.println(count);
	}
	
	// 작성된 리뷰 목록 가져오기
	@Test
	public void reviewList() {
		int reviewCount = reviewDao.reviewCount(255);
//		System.out.println("받아온 리뷰 개수: " + reviewCount);
		ReviewPageHandler rph = new ReviewPageHandler(reviewCount, 1, 5);
		
		System.out.println(rph);
		
		Map map = new HashMap();
		map.put("product_id", 255);
		map.put("offset", (rph.getPage()-1)*rph.getPageSize());
		map.put("pageSize", rph.getPageSize());
		
		List<Review> list = reviewDao.reviewList(map);
		System.out.println(list);
//		
//		Map map = new HashMap();
//		map.put("pageSize", map)
//		List<Review> list = reviewDao.reviewList(255);
//		System.out.println(list);
		
	}
	
	// 작성된 리뷰 수정
	@Test
	public void reviewUpdate() {
		Review review = new Review();
		review.setProduct_id(255);
		review.setUser_id("geumsung7769");
		review.setContent("33933333333");
		review.setRating(5);
		
		
		reviewDao.update(review);		
	}
	
	// 리뷰 삭제 테스트
	@Test
	public void deleteTest(){
		Review review = new Review();
		review.setProduct_id(255);
		review.setUser_id("geumsung7769");
		
		int count = reviewDao.delete(review);
		
		System.out.println(count);
		assertTrue(count == 1);
	}
}
