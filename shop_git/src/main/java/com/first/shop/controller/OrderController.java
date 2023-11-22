package com.first.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.dto.InstantOrderProduct;
import com.first.shop.dto.InstantOrderProducts;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.User;
import com.first.shop.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	// 임시 상품 등록
	@PostMapping("/postOrder")
	public String register(int product_id, String id, int quantity) {
		System.out.println("전달값 출력:" + product_id + "," + id + "," + quantity);
		orderService.registerOrders(product_id, id, quantity);
		return "redirect:/";
	}
	
	
	// 장바구니 -> 주무페이지 -> 주문
	@PostMapping("/postOrder2")
	public String order(OrderProductList orderProductList, OrdersList ordersList, BindingResult result) {
		System.out.println("객체검증:" + result);
		System.out.println("주문 상품 :" + orderProductList);
		System.out.println("주문 정보 :" + ordersList);
		
		return "redirect:/";
	}
	
	@GetMapping("/instantOrder/{user_id}")
	public String instantOrder(@PathVariable String user_id, InstantOrderProducts instantOrderProducts, Model model) {

		System.out.println("받아온 값 테스트 : "+ instantOrderProducts);
		// 전달할 상품 정보를 리스트로 만들어 반환한다.
		List<InstantOrderProduct> list = instantOrderProducts.getOrderProducts();
		// 주문한 유저 정보도 반환
		User user = orderService.getUserInfo(user_id);
		
		model.addAttribute("instantList", list);
		model.addAttribute("orderUser", user);
		return "/order/orderPage";
	}
	
	
	
}
