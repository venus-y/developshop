package com.first.shop.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		String user_id = connectUser.getId();
		
		List<Cart> cartList = cartService.getCartList(user_id);
		model.addAttribute("cartList", cartList);
		
		return "cartPage";
		
	}
	
	
	// 장바구니 등록
	@ResponseBody
	@PostMapping("/register")
	public String register(Cart cart) throws UnsupportedEncodingException {
		// 뷰에서 이미지정보를 JSON으로 요청하니 값이 이상하게 온다.
		// 뷰에서 받아온 상품아이디를 통해 해당 상품의 이미지를 얻어오는 것으로 대체
		Product forCart = cartService.CartProductImage(cart.getProduct_id());
		
		cart.setProduct_image(forCart.getProduct_image());
		cart.setProduct_thumbimage(forCart.getProduct_thumbimage());
		
		cartService.registerCart(cart);
		
		return "success";
	}
	
	// 장바구니 수정
	@PostMapping("/update")
	@ResponseBody
	public String update(@RequestBody Cart cart, BindingResult result) {
		System.out.println("result="+result);
		// 장바구니 페이지로부터 들어온 정보에 맞게 기존의 장바구니를 새롭게 수정한다.
		System.out.println("장바구니 객체 확인" + cart);
		cartService.updateCart(cart);
		return "success";
	}
	
	// 장바구니 삭제
	@PostMapping("/remove/{cart_id}")
	@ResponseBody
	public String remove(@PathVariable Integer cart_id) {
		
//		System.out.println("BindingResult:"+result);
		// 장바구니 번호 잘 넘어왔는지 확인
		System.out.println("cart_id:"+cart_id);
		// 삭제 요청
		cartService.removeCart(cart_id);
		return "success";
	}
}
