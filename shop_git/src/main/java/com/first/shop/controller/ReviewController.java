package com.first.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;
import com.first.shop.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	// 댓글 등록 페이지 불러오기
	@GetMapping("/getWrite/{user_id}")
	public String getWrite(@PathVariable("user_id")String user_id, int product_id, Model model) {
		// 리뷰 작성하는데 필요한 상품정보를 DB로부터 받아온다.
		Product productInfo = reviewService.review_ProductInfo(product_id);
		// 모델에 담아 반환
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("user_id", user_id);
		return "/reviewForm";
	}
	
	
	// 댓글 등록 요청
	@PostMapping("/writeReview")
	@ResponseBody
	public void postWrite(Review review) {
		// ajax 요청으로 받아온 댓글 정보를 넘겨준다.
		reviewService.writeReview(review);		
	}
	
}
