package com.first.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.dto.BrandSearchCondition;
import com.first.shop.dto.Category;
import com.first.shop.dto.CategoryPageHandler;
import com.first.shop.dto.CategorySearchCondition;
import com.first.shop.dto.PageHandler;
import com.first.shop.dto.Product;
import com.first.shop.dto.Review;
import com.first.shop.dto.User;
import com.first.shop.service.ProductService;
import com.first.shop.service.ReviewService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	private final ReviewService reviewService;

	
	public ProductController(ProductService productService, ReviewService reviewService) {
		this.productService = productService;
		this.reviewService = reviewService;
	}
	
	// 상품 목록 시 이미지 조회를 위해 이미지 저장 경로를 넘겨준다.
	@Autowired
	private String uploadPath;

	
	//상품 정보 조회
	@GetMapping("/productInfo")
	public String productInfo(HttpServletRequest request ,int product_id, Model model) {
		// 미리보기 이미지로부터 받아온 상품번호에 해당하는 상품정보를 db에서 받아온다.
		Product productInfo = productService.getProductInfo(product_id);
		// 상품정보를 모델에 담아 뷰에 넘겨준다.
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Review review = new Review();
		
		//세션에 유저객체가 존재하지 않는 상태 즉 로그인 하지 않은 상태면 아래 코드들은 실행 x
		if(user != null) {
			// 세션에서 받아온 id를 매개변수로 받아온 상품id를 Review 객체에 담는다.
			
			review.setUser_id(user.getId());
			review.setProduct_id(product_id);
		}
				
		// 접속한 유저가 해당 상품을 구매한 이력이 있는지 검사
		int orderHistoryCheck = reviewService.check_OrderHistory(review);
		
		
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("orderHistoryCheck", orderHistoryCheck);
		return "/product/productInfo";
	}
	
	//카테고리별 상품 조회
	@GetMapping("/categorySet")
	public String categorySet(CategorySearchCondition csc, Model model) {
		// 페이지와 페이지사이즈정보가 없을 경우 각각 1, 8로 세팅
//		if(page == null && pageSize == null) {
//			page = 1;
//			pageSize = 2;
//		}
		// 상품들의 총 개수를 받아온다.
		int productCount = productService.getProduct_CategorySet_Count(csc);
		
		// 페이징
		CategoryPageHandler ph = new CategoryPageHandler(productCount, csc);
		
		//카테고리별로 분류한 상품페이지 정보를 받아온다.
		List<Product> productList = productService.getProduct_CategorySet(csc);
		
		// 상품목록을 모델에 담는다.
		model.addAttribute("productList", productList);
		// 페이지 정보를 담는다.
		model.addAttribute("ph", ph);
		
		
		
		
		//2차 분류가 있을 경우
		if(csc.getDetail() != null && csc.getDetail() != "") {
			return "/category/"+csc.getViewName() + "/" + csc.getDetail();
		}
		
		return "/category/"+csc.getViewName();
	}
	
	// 브랜드별 분류 페이지
	@GetMapping("/sortBrand")
	public String sortBrand(Model model) {
		List<Category> brandList = productService.sortByBrand();
		
		model.addAttribute("brandList", brandList);
		
		return "/category/brand";
	}
	
	// 브랜드 상품페이지로 이동
	@GetMapping("/brandPage")
	public String brandPage(BrandSearchCondition bsc, Model model ) {
		
		
		// 페이징 처리 필요
//		처음 화면에 들어왔을 경우 페이지와 페이지사이즈 정보가 없으니 기본값세팅
		
//		  if(page == null && pageSize == null) { page = 1; pageSize = 8; }
		 
		// 브랜드 상품의 총 수량을 DB에서 받아온다.
		int totalCount = productService.getBrandProduct_SearchCount(bsc);
		
		
				
		// 페이징 처리
		
		  PageHandler ph = new PageHandler(totalCount, bsc);
		  		
		  List<Product> productList = productService.getBrandProduct_Search(bsc);
		  model.addAttribute("productList", productList);
		  model.addAttribute("ph", ph);
			 
		 
		
		return "/category/brandProduct";
	}
	
}
