package com.first.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.dto.PageHandler;
import com.first.shop.dto.Product;
import com.first.shop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// 상품 목록 시 이미지 조회를 위해 이미지 저장 경로를 넘겨준다.
	@Autowired
	private String uploadPath;
	
	// 상품 목록 조회 
	@GetMapping("/productList")
	public String getProductList(Integer page, Integer pageSize, Model model) {
		// 총 상품 수를 받아온다.
		// 받아온 상품 수를 페이지핸들러에 넘겨준 후 페이징 처리를 한다.
		int totalCnt = productService.countAllProduct();
		
		System.out.println("페이지 잘 넘어갔나 확인" + page);
		System.out.println("페이지사이즈  잘 넘어갔나 확인" + pageSize);

		
		// 처음 페이지에 들어갈 경우엔 페이지정보가 없기 때문에 기본 값을 정해준다.
		if(page == null && pageSize == null) {
			page = 1;
			pageSize = 2;
		}
		
		// 페이지에서 다시 다른 페이지로 이동할 경우 페이지정보를 컨트롤러에게 전달해줘야 한다.
		PageHandler ph = new PageHandler(page, pageSize, totalCnt);
		
		// 페이지 오프셋과 페이지사이즈 정보를 map에 담아준다.
		Map map = new HashMap();
		map.put("offset", (ph.getPage()-1) * ph.getPageSize());
		map.put("pageSize", ph.getPageSize());
		
		// map에 담아준 오프셋과 페이지사이즈 조건에 맞게 상품정보를 받아온다.
		List<Product> list = productService.getPageList(map);
		
		// 페이지정보를 모델에 담는다.
		model.addAttribute("list", list);
		model.addAttribute("ph", ph);
		model.addAttribute("uploadPath", uploadPath);
		
		return "/product/productList";
	}
	
	//상품 정보 조회
	@GetMapping("/productInfo")
	public String productInfo(int product_id, Model model) {
		// 미리보기 이미지로부터 받아온 상품번호에 해당하는 상품정보를 db에서 받아온다.
		Product productInfo = productService.getProductInfo(product_id);
		// 상품정보를 모델에 담아 뷰에 넘겨준다.
		model.addAttribute("productInfo", productInfo);
		return "/product/productInfo";
	}
	
	//카테고리별 상품 조회
	@GetMapping("/categorySet")
	public String categorySet(int category_code, String viewName, String detail, Model model, Integer page, Integer pageSize) {
				
		// 페이지와 페이지사이즈정보가 없을 경우 각각 1, 8로 세팅
		if(page == null && pageSize == null) {
			page = 1;
			pageSize = 2;
		}
		// 상품들의 총 개수를 받아온다.
		int productCount = productService.getProduct_CategorySet_Count(category_code);
		
		// 페이징
		PageHandler ph = new PageHandler(page, pageSize, productCount);
		
		
		
		// 상품을 받아올 때 필요한 조건들을 맵에 담는다.
		Map map = new HashMap();
		// 페이지 오프셋, 페이지 사이즈, 카테고리 코드 
		map.put("offset", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("category_code", category_code);
		
		//카테고리별로 분류한 상품페이지 정보를 받아온다.
		List<Product> productList = productService.getPageList(map);
		
		System.out.println(productList);
		
		// 상품목록을 모델에 담는다.
		model.addAttribute("productList", productList);
		// 페이지 정보를 담는다.
		model.addAttribute("ph", ph);
		
		
		//2차 분류가 있을 경우
		if(detail != null) {
			return "/category/"+viewName + "/" + detail;
		}
		
		return "/category/"+viewName;
	}
	
}
