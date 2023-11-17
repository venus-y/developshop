package com.first.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/postOrder")
	public String register(int product_id, String id, int quantity) {
		System.out.println("전달값 출력:" + product_id + "," + id + "," + quantity);
		orderService.registerOrders(product_id, id, quantity);
		return "redirect:/";
	}
	
}
