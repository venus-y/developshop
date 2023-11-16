package com.first.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.shop.dto.Cart;
import com.first.shop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
		
	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// 장바구니 등록
	@ResponseBody
	@PostMapping("/register")
	public String register(Cart cart) {
		System.out.println(cart);
		cartService.registerCart(cart);
		return "success";
	}
}
