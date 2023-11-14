package com.first.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.shop.dto.Category;
import com.first.shop.dto.Product;
import com.first.shop.service.AdminService;
import com.first.shop.service.ProductService;
import com.first.shop.utils.UploadFileSettings;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	// 업로드 경로를 주입받는다.
	private String uploadPath;
	
	private final AdminService adminService;
	
	private final ProductService productService;
	
	public AdminController(AdminService adminService, ProductService productService) {
		this.adminService = adminService;
		this.productService = productService;
	}
	
	// 상품 등록 페이지
	@GetMapping("/registerProductForm")
	public void getRegister(Model model) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Category> list  = adminService.getCategoryList();
	
		// 자바 객체를 String 타입의 JSON 객체로 변환해준다.
		String cateList = objectMapper.writeValueAsString(list);
		// 받아온 카테고리 리스트를 모델에 담아 뷰로 반환
		model.addAttribute("cateList", cateList);
	}
	
	// 상품 수정
	@GetMapping("/updateProduct")
	public String update(int product_id, Model model ) {
		// 상품정보를 모델에 담아 수정 페이지로 전달한다.
		Product updatingProduct = productService.getProductInfo(product_id);
		model.addAttribute("productInfo", updatingProduct);
		return "/admin/updateProduct";
	}
	
	
	// 상품 등록 요청
	@PostMapping("/registerProduct")
	// 매개변수로 상품정보와 파일정보를 받는다.
	public String postRegister(Product product, MultipartFile file) throws IOException {
		
		System.out.println("상품정보 담기는지 테스트:"+product);
		System.out.println("상품이미지:"+file);
		System.out.println("경로 잘 가져오는지 테스트:" + uploadPath);
		
		System.out.println("파일 잘 넘어오나 테스트 " + file);
		
		// uploadPath 값을 imgUploadPath에 넣어준다.
		String imgUploadPath = uploadPath;
		
		
		System.out.println("imgUploadPath:"+imgUploadPath);
		
		// 현재 년/월/일을 계산하여 경로를 생성한다.
		String yyMMddPath = UploadFileSettings.calcPath(imgUploadPath);
		
		System.out.println("yyMMddPath:" +yyMMddPath);
		
		// 파일명 변수
		String fileName = null;
		
		// 매개변수로 전달받은 file이 실제로 존재하는지 확인
		if(file != null) {
			// DB에 파일정보를 저장하기 위해 파일업로드 후 파일명을 반환받는다.
			// file.getOriginalFilename(), file.getBytes() -> 원래 파일명과 파일의 데이터를 받아오는 메서드
			fileName  = UploadFileSettings.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), yyMMddPath);
		} else {
			// 존재하지 않는 파일일 경우 이미지없음 파일을 저장한다.
			fileName = uploadPath + File.separator + "images" + File.separator + "noProduct.png";
		}
		
		System.out.println("fileName:"+fileName);
		

		
		
		//상품정보에 이미지정보를 추가한다. 파일 구분자 + 경로 + 구분자 + 파일명
		product.setProduct_image(yyMMddPath + File.separator + fileName);
		//썸네일정보를 추가해준다. 년월일 + 구분자와 구분자 사이에 "s"를 추가하고 다음에 오는 구분자 뒤에 "s_"를 추가하고 뒤에 파일명을 덧붙인다.
		product.setProduct_thumbimage(yyMMddPath + File.separator + "s" + File.separator + "s_" + fileName);
		
		System.out.println("image info:"+product.getProduct_image());
		System.out.println("tImage info" + product.getProduct_thumbimage());
		
		// 뷰로부터 넘어온 상품 객체와 상품이미지를 서비스단으로 넘긴다.
		adminService.registerProduct(product);
		// 상품 등록 후 등록리스트로 가야함 , 일단 홈으로 리턴
		return "redirect:/";
	}
	 
	
	
	
	
	
	// 카테고리 불러오기
//	@GetMapping("/getCategoryList")
//	public void getCategory(Model model) throws JsonProcessingException {
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		List<Category> list  = adminService.getCategoryList();
//	
//		// 자바 객체를 String 타입의 JSON 객체로 변환해준다.
//		String cateList = objectMapper.writeValueAsString(list);
//		// 받아온 카테고리 리스트를 모델에 담아 뷰로 반환
//		
//		
//	}
	
}
