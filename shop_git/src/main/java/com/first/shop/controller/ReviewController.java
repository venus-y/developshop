package com.first.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.shop.dto.Product;
import com.first.shop.dto.Review;
import com.first.shop.dto.ReviewPageHandler;
import com.first.shop.dto.ReviewPageInfo;
import com.first.shop.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	// 리뷰 등록 페이지 불러오기
	@GetMapping("/getWrite/{user_id}")
	public String getWrite(@PathVariable("user_id")String user_id, int product_id, Model model) {
		// 리뷰 작성하는데 필요한 상품정보를 DB로부터 받아온다.
		Product productInfo = reviewService.review_ProductInfo(product_id);
		// 모델에 담아 반환
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("user_id", user_id);
		return "/reviewForm";
	}
	
	// 리뷰 수정 페이지 불러오기
		@GetMapping("/reviewUpdate/{user_id}")
		public String reviewUpdate(@PathVariable("user_id")String user_id, Integer product_id, Model model) {
			// 매개변수로 넘겨줄 리뷰객체
			Review paramReview = new Review();
			paramReview.setProduct_id(product_id);
			paramReview.setUser_id(user_id);
			// 리뷰를 수정하기 위해 기존에 작성됐던 리뷰정보를 가져온다.
			Review reviewInfo = reviewService.get_ReviewInfo(paramReview);
			model.addAttribute("reviewInfo", reviewInfo	);
			return "/reviewUpdate";
		}
		
	// 리뷰 수정
	@PostMapping("/reviewUpdate")
	@ResponseBody
	public void postReviewUpdate(Review review) {
		// 리뷰 수정 페이지로부터 받아온 정보를 넘겨준다.
		reviewService.update_Review(review);
	}
	
	// 리뷰 삭제
	@PostMapping("/reviewDelete")
	@ResponseBody
	public void reviewDelete(Review review) {
		// 리뷰 수정 페이지로부터 받아온 정보를 넘겨준다.
		reviewService.delete_Review(review);
	}
	
	// 리뷰 등록 요청
	@PostMapping("/writeReview")
	@ResponseBody
	public void postWrite(Review review) {
		// ajax 요청으로 받아온 리뷰 정보를 넘겨준다.
		reviewService.writeReview(review);		
	}
	
	
	// 리뷰 작성한 적 있는지 확인
	@PostMapping("/checkHistory")
	@ResponseBody
	public int checkHistory(@RequestBody Review review) {
		System.out.println(review +" 전달받음");
		// DB에 등록된 리뷰 정보가 있는지 확인
		int count = reviewService.check_ReviewHistory(review);
		// 뷰로 다시 반환
		return count;
	}
	
	// 상품에 작성된 리뷰 목록 가져오기
	@GetMapping(value = "/reviewList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ReviewPageInfo reviewList(int product_id, Integer page, Integer pageSize){
		// 상품정보 페이지에서 받아온 상품번호로 DB에 있는 총 리뷰개수를 조회한 후 받아온다.
		int totalReviewCount = reviewService.getReviewCount(product_id);
		
		// 처음 리뷰목록을 가져올 경우 page와 pageSize값이 넘어오지 않게 된다.
		// 따라서 null예외를 방지하기 위해 기본값을 셋팅
		if(page == null && pageSize == null) {
			page = 1;
			pageSize = 5;
		}
		
		// 리뷰 페이지 핸들러 객체 생성
		ReviewPageHandler rph = new ReviewPageHandler(totalReviewCount, page, pageSize);
		
		//Map에 페이지, 페이지사이즈, 상품정보를 담아 넘겨준다.
		Map map = new HashMap();
		map.put("offset", (rph.getPage() - 1) * rph.getPageSize());
		map.put("pageSize", rph.getPageSize());
		map.put("product_id", product_id);
		
		System.out.println(rph);
		
		
		List<Review> reviewList =  reviewService.getReviewList(map);
		System.out.println("리뷰 목록 출력");
		for(int i=0; i < reviewList.size(); i++) {
			System.out.println(reviewList.get(i));
		}
		
		// 리뷰페이지 정보 객체에 받아온 리뷰목록과 페이징정보를 담는다.
		ReviewPageInfo reviewPageInfo = new ReviewPageInfo();
		reviewPageInfo.setReviewList(reviewList);
		reviewPageInfo.setRph(rph);
		
		return reviewPageInfo;
	}
}
