package com.first.shop.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.shop.dao.OrderDao;
import com.first.shop.dto.Cart;
import com.first.shop.dto.CartList;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.Orders;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.Product;
import com.first.shop.dto.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	
	// 현재 사용 x
	// 상품 구매시 유저의 포인트사용정보, 잔액 정보가 업데이트되고 상품의 재고수량이 변경되며 최종적으로 주문정보가 등록된다.
	@Override
	@Transactional
	// 주문 처리 중 하나라도 잘못되면 원복돼야 한다.
	public int registerOrders(int product_id, String id, int quantity) {
		// 세일가를 고려해야한다. 뷰에서 추출한 세일가를 product의 salePrice와 name을 맞춰 매핑한다.
		// 주문한 유저의 정보와 상품의 정보를 가져온다. -> 업데이트 해주기 위해서
		User user = orderDao.user(id);
		Product product = orderDao.product(product_id);	
		// 물품의 가격만큼 유저의 잔액을 차감한다.
		user.setMoney(user.getMoney()-product.getPrice());
		// 물품의 재고 - 요청한 상품수량만큼 재고를 차감한다. 
		product.setStock(product.getStock()-quantity);
		// 유저,상품 정보를 갱싱한다.
		orderDao.update(user);
		orderDao.update(product);
		
		orderInfoRegister(quantity, user);
		
		return 1;
	}

	// 주문정보 생성 메서드
	private void orderInfoRegister(int quantity, User user) {
		// 주문정보를 생성한다.
		Orders orders = new Orders();
		// 주문번호를 생성한다.
		// UUID 클래스로 랜덤한 문자열 생성
		UUID uuId = UUID.randomUUID();
		// 주문번호의 앞에는 날짜정보가 올 수 있게 한다. 날짜정보를 얻어오기 위해 Calendar 객체를 생성
		Calendar cal = Calendar.getInstance();
		// 출력하고 싶은 날짜 형식을 만든다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 날짜를 지정한 양식의 문자열로 변환한다.
		String calString = sdf.format(cal.getTime());
		
		
		String orderId = calString + "_" + uuId;
		System.out.println("orderId: "+orderId);
		
		// 주문번호, 주문자, 총수량, 배송상태를 셋팅한다.
		orders.setOrder_id(orderId);
		// 이것도 총수량이 돼야한다. 현재는 상품 하나를 기준으로 주문하고 있음
		orders.setTotal_amount(quantity);
		orders.setUser_id(user.getId());
		orders.setStatus("배송준비");
		// 주문정보 등록
		orderDao.register(orders);
	}

	
	// 유저정보 받아오기
	@Override
	public User getUserInfo(String user_id) {
		return orderDao.user(user_id);
	}

	// 주문 처리
	@Override
	@Transactional
	public int registerOrders2(OrdersList ordersList, OrderProductList orderProductList, CartList cartList, int used_point) {
		// 동시에 주문한 상품들이기 때문에 배송비는 한번만 계산한다.
		// 맨 앞에 요소의 배송비만큼 할당한다.
		int deliveryCost = ordersList.getOrdersList().get(0).getDelivery_cost();
		
		System.out.println("==========================");
		System.out.println("배송비 출력:" + deliveryCost);

		// 주문상품 목록을 순회하면서 상품가격과 적립포인트를 구해준다.
		int totalPrice = 0;
		int totalSavePoint = 0;
		
		List<OrderProduct> opList = orderProductList.getOrderProductList();
		
		for(int i = 0; i < opList.size();  i++) {
			// 각 주문상품별 총 상품가격과 총 적립포인트를 각각 더해주고 상품의 재고를 차감한다.
			totalPrice += opList.get(i).getPrice();
			totalSavePoint += opList.get(i).getSavepoint();
			
			// 재고를 갱신할 상품의 정보를 받아온다.
			Product product = orderDao.product(opList.get(i).getProduct_id());
			System.out.println("상품 재고 갱신 전:" + product.getStock());
			System.out.println("==========================");
			System.out.println("현재 순회중인 상품명:" + product.getProduct_name());
			// 상품수량만큼 차감한다.
			product.setStock(product.getStock()-opList.get(i).getQuantity());
			
			// db에 있는 상품정보를 갱신
			orderDao.update(product);
			System.out.println("======================");
			
			System.out.println("상품 재고 갱신 후:" + product.getStock());
			System.out.println("======================");
			
		}
		
		System.out.println("총 합산 가격:" + totalPrice);
		System.out.println("==========================");
		
		System.out.println("총 합산 포인트:" + totalSavePoint);
		System.out.println("======================");
		
		// 결제할 유저 정보를 가져온다.
		User user = orderDao.user(ordersList.getOrdersList().get(0).getUser_id());
		// 유저의 잔액에서 결제금액만큼 차감한다.
		
		//유저 잔액 출력
		System.out.println("유저의 잔액(before):"+ user.getMoney());
		System.out.println("==========================");
		
		// 최종 결제 금액
		System.out.println("최종 결제 금액:" + (totalPrice + deliveryCost));
		System.out.println("===========================");
		
		user.setMoney(user.getMoney() - (totalPrice + deliveryCost) - used_point);
		System.out.println("유저의 잔액(after):"+ user.getMoney());
		System.out.println("==========================");

		//유저의 사용한 포인트를 차감시키고 남은 포인트를 출력
		
		user.setPoint(user.getPoint() - used_point);
		System.out.println("유저의 잔여포인트(before)"+ user.getPoint());
		System.out.println("==========================");
		
		
		
		
		//장바구니에 담겨있던 상품정보를 지운다.
		List<Cart> deleteList = cartList.getCartList();
		for(int i=0; i<deleteList.size(); i++) {
			orderDao.delete(deleteList.get(i));
		}
		
		
		// 유저의 잔여포인트에 총적립포인트만큼 가산
		user.setPoint(user.getPoint() + totalSavePoint);
		System.out.println("유저의 잔여포인트(after)"+ user.getPoint());
		
		
		
		// 주문 정보, 주문 상품 정보 생성, 배송정보 생성
		List<Orders> oList = ordersList.getOrdersList();
		
		int ordersInfoCheck = 0;
		int orderProductCheck = 0;
		
		for(int i=0; i<oList.size(); i++) {
			Orders orders = oList.get(i);
			
			// 주문정보 등록
			ordersInfoCheck = orderInfoRegister2(orders);
			// 주문상품정보 등록
			orderProductCheck = orderProductRegister(orders.getOrder_id(), opList.get(i));
			
			if(ordersInfoCheck == 1 && orderProductCheck == 1) {
				System.out.println("주문정보 및 주문상품정보 등록 완료!");
				
			}
		}	
		   // 정상적으로 처리됐을 경우 1을 반환
		   if(ordersInfoCheck == 1 && orderProductCheck == 1)
			   return 1;
		   
		   // 잘 안됐을 경우엔 0 반환한다.
		   	   return 0;
		   
	}
	// 주문정보 생성 메서드
		private int orderInfoRegister2(Orders orderInfo) {
			// 주문번호를 생성한다.
			// UUID 클래스로 랜덤한 문자열 생성
			UUID uuId = UUID.randomUUID();
			// 주문번호의 앞에는 날짜정보가 올 수 있게 한다. 날짜정보를 얻어오기 위해 Calendar 객체를 생성
			Calendar cal = Calendar.getInstance();
			// 출력하고 싶은 날짜 형식을 만든다.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// 날짜를 지정한 양식의 문자열로 변환한다.
			String calString = sdf.format(cal.getTime());
			
			
			String orderId = calString + "_" + uuId;
			System.out.println("orderId: "+orderId);
			
			// 주문번호, 배송상태를 셋팅한다.
			// 이것도 총수량이 돼야한다. 현재는 상품 하나를 기준으로 주문하고 있음
			orderInfo.setOrder_id(orderId);
			orderInfo.setStatus("배송준비");
			
			System.out.println("주문 정보 출력:" + orderInfo);
			// 주문정보 등록
			return orderDao.register(orderInfo);
		}
		
		// 주문상품정보 등록
		private int orderProductRegister(String order_id, OrderProduct orderProduct) {
			orderProduct.setOrder_id(order_id);
			System.out.println("주문상품 정보 출력:" + orderProduct);
			
			return orderDao.order_product(orderProduct);
			
		}
		
		// 배송정보 등록
		

//	@Override
//	public int updateUser(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateProduct(Product product) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
