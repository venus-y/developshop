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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.first.shop.dto.CartList;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.TempOrderProduct;
import com.first.shop.dto.TempOrderProducts;
import com.first.shop.dto.User;
import com.first.shop.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	// 장바구니 -> 주문페이지 -> 주문
	@PostMapping("/postOrder")
	public String order( OrdersList ordersList, OrderProductList orderProductList, CartList cartList,
			@RequestParam(defaultValue = "0", required = false) int used_point,
			BindingResult result, RedirectAttributes rattr) throws Exception{
		System.out.println("객체검증:" + result);
		System.out.println("주문 정보 :" + ordersList);
		System.out.println("==================================================================================");
		System.out.println("주문 상품 :" + orderProductList);
		System.out.println("==================================================================================");
		System.out.println("삭제할 장바구니 정보:" + cartList);
		System.out.println("==================================================================================");
		System.out.println("사용 포인트 정보 : "+ used_point);
		
		try {
			int orderResult = orderService.registerOrders2(ordersList , orderProductList , cartList, used_point);
			
			if(orderResult != 1) {
				throw new Exception("Order failed");
			}else if(orderResult == 1) 
				// 잘 처리됐을 경우 주문처리가 잘 완료됐다는 메세지를 저장
				rattr.addFlashAttribute("msg", "ORDER_SUCCESS");
				return "redirect:/";	

		}catch(Exception e) {
			// 문제가 발생했을 경우 오류가 발생했단 메세지를 남긴다.
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ORDER_FAILED");
			return "redirect:/";
		}

		
		
		
	}
	//주문화면
	@GetMapping("/getOrder/{user_id}")
	public String getOrder(@PathVariable String user_id, TempOrderProducts tempOrderProducts,
			BindingResult result , Model model) {
		
		System.out.println("값에 문제가 있나>"+result);
		System.out.println("받아온 값 테스트 : "+ tempOrderProducts);
		// 전달할 상품 정보를 리스트로 만들어 반환한다.
		List<TempOrderProduct> list = tempOrderProducts.getOrderProducts();
		
		// 장바구니에서 넘어온 데이터에는 총 합산가격과 총 적립포인트 정보가 세팅돼있지만 상품정보->즉시주문의 경우에는
		// 해당값들이 세팅돼있지 않다, 따라서 값을 세팅해준다. 즉시주문의 경우 한 개의 상품만 값을 세팅하면 된다.
		// 배열의 [0] 번째 요소가 합산가격, 합산포인트 정보가 없다면 즉시주문을 한 경우이니 검사 후 세팅
		
		if(list.get(0).getTotalprice() == 0 && list.get(0).getSaleprice() == 0 && list.get(0).getTotalsavepoint()==0) {
			list.get(0).setTotal();
			System.out.println(list.get(0));
		}
		
		// 주문한 유저 정보도 반환
		User user = orderService.getUserInfo(user_id);
		
		model.addAttribute("tempList", list);
		model.addAttribute("orderUser", user);
		return "/order/orderPage";
	}
	

	// 상품정보 -> 주문화면
	@PostMapping("/fromInstantOrder/{user_id}")
	public String instantOrder(@PathVariable String user_id, TempOrderProducts tempOrderProducts, BindingResult result, Model model) {
//		System.out.println("전달값 출력:" + product_id + "," + id + "," + quantity);
//		orderService.registerOrders(product_id, id, quantity);
		System.out.println("오류 검출 결과:" + result);
		System.out.println("받아온 정보 출력:"+ tempOrderProducts);
		
		// 상품정보페이지에서 즉시주문을 누를 경우 하나의 상품정보만 주문페이지로 전달하면 된ㄷ.
		// InstantOrderProducts의 첫번째 요소에 총 합산가격과, 합산포인트 정보를 셋팅한다.
		for(TempOrderProduct product : tempOrderProducts.getOrderProducts()) {
			product.setTotal();
			// 셋팅 결과 출력
			System.out.println("합산가격, 합산포인트 셋팅 결과 출력:" + product);
		}
		
		return "redirect:/";

	}
	
	
	
	
}
