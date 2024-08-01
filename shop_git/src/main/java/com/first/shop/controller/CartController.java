package com.first.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;
import com.first.shop.dto.User;
import com.first.shop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// 장바구니페이지 불러오기
	@GetMapping("/getCart")
	public String cartPage(Model model, HttpServletRequest request) {
		// 접속한 유저의 장바구니를 가져온다.
		HttpSession session = request.getSession();
		// 세션에서 유저 아이디를 받아온다.
		User connectUser = (User)session.getAttribute("user");
		
		// 세션 만료될 경우 재로그인 처리
		try {			
			String user_id = connectUser.getId();
			List<Cart> cartList = cartService.getCartList(user_id);
			// 장바구니에서 전달되는 요청인지를 체크하기 위한 변수 선언
			boolean cartCheck = true;
			model.addAttribute("cartList", cartList);
			model.addAttribute("cartCheck", cartCheck);
		} catch(NullPointerException e) {
			
			return "redirect:/login/getLogin";
		}
			
		return "cartPage";
		
	}
	
	
	// 장바구니 등록
	@ResponseBody
	@PostMapping("/register")
	public String register(Cart cart) throws Exception {
		// 뷰에서 이미지정보를 JSON으로 요청하니 값이 이상하게 온다.
		// 뷰에서 받아온 상품아이디를 통해 해당 상품의 이미지를 얻어오는 것으로 대체
		Product forCart = cartService.CartProductImage(cart.getProduct_id());
		
		cart.setProduct_image(forCart.getProduct_image());
		cart.setProduct_thumbimage(forCart.getProduct_thumbimage());
		
		int result = cartService.registerCart(cart);
		// result가 0일 경우 이미 DB에 동일한 유저가 동일한 상품을 등록하려고 시도한 경우
		if(result == 0) {
			return "same product already exists";
		}
		
		return "success";
	}
	
	// 장바구니 수정
	@PostMapping("/update")
	@ResponseBody
	public String update(@RequestBody Cart cart) {
		// 장바구니 페이지로부터 들어온 정보에 맞게 기존의 장바구니를 새롭게 수정한다.
		
		int checkResult = cartService.updateCart(cart);
		
		if(checkResult == 0) {
			return "failed";
		}

		return "success";
	}
	
	// 장바구니 삭제
	@PostMapping("/remove/{cart_id}")
	@ResponseBody
	public String remove(@PathVariable Integer cart_id) {
		
//		
		// 장바구니 번호 잘 넘어왔는지 확인
		
		// 삭제 요청
		cartService.removeCart(cart_id);
		return "success";
	}
	
	// 장바구니에 담긴 상품에 실재고를 조회한 후 뷰로 반환
	@GetMapping("/check/{product_id}")
	@ResponseBody
	public int check(@PathVariable Integer product_id) {
		
		
		int stock = cartService.getStock_int(product_id);
		
		
		return stock;
	}
}
