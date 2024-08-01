package com.first.shop.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.shop.dao.OrderDao;
import com.first.shop.dto.CartList;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.OrderProductandCartList;
import com.first.shop.dto.Orders;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.Product;
import com.first.shop.dto.Purchase_History;
import com.first.shop.dto.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	

	// 주문 처리
	@Override
	@Transactional
	public int registerOrder(OrdersList ordersList, OrderProductList orderProductList,
			CartList cartList, int used_point, boolean cartcheck) {
				
		List<OrderProduct> orderProducts = orderProductList.getOrderProductList();
		
		
		List<Integer> productIds = orderProducts
									.stream()
									.map(OrderProduct::getProduct_id)
									.collect(Collectors.toList());
		
		List<Product> productByIds = orderDao.productByIds(productIds);
		
		
		IntStream
				.range(0, orderProducts.size())
				.forEach(i-> productByIds
				.get(i)
				.setStock(orderProducts.get(i).getQuantity()
			 )
		);
				
		orderDao.updateStock(productByIds);
		
	   	    
	    int deliveryCost = ordersList
							.getOrdersList()
							.get(0)
							.getDelivery_cost();
	    
	    int totalPrice = orderProducts
							.stream()
							.mapToInt(OrderProduct::getPrice)
							.sum();

	    int totalSavePoint = orderProducts
								.stream()
								.mapToInt(OrderProduct::getSavepoint)
								.sum();
				
		User user = orderDao.user(
							 ordersList
							 	.getOrdersList()
							 	.get(0)
							 	.getUser_id()
							 );
			
		updateBalance(used_point, deliveryCost, totalPrice, totalSavePoint, user);
		
		orderDao.updateUser(user);
		
		if(cartcheck) {
			orderDao.deleteCart(cartList.getCartList());
		}				
		
		List<Orders> orderList = ordersList.getOrdersList();
		
		int ordersInfoCheck = registerOrderInfo(orderList);
		
		List<String> orderIds = orderList
				.stream()
				.map(Orders::getOrder_id)
				.collect(Collectors.toList());
		
		int orderProductCheck = registerOrderProductInfo(orderIds, orderProducts);
		

	    if(ordersInfoCheck == ordersList.getOrdersList().size() 
			   && orderProductCheck == orderProductList.getOrderProductList().size()) {
		   return 1;
	    }
	   	   return 0;	   
	}


	private void updateBalance(int used_point, int deliveryCost, int totalPrice, int totalSavePoint, User user) {
		user.setMoney(user.getMoney() - (totalPrice + deliveryCost) - used_point);
		
		user.setPoint(user.getPoint() - used_point + totalSavePoint);
		
	}
	
	// 주문상품정보 등록
	private int registerOrderProductInfo(List<String> orderIds, List<OrderProduct> orderProducts) {
		IntStream.range(0, orderProducts.size())
				 .forEach(
							 i -> orderProducts
							 		.get(i)
							 		.setOrder_id(orderIds.get(i)
						 )
		);
		
		return orderDao.registerOrderProduct(orderProducts);
		
	}
	
	private int registerOrderInfo(List<Orders> orderList) {
				
			orderList.stream().forEach(o -> {
				o.setOrder_id(createOrderId());
				o.setStatus("배송준비");
				o.setPayment_method("일반결제");
			}
		);
		
		return orderDao.registerOrder(orderList);
	}
	
	private String createOrderId() {
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
		return orderId;
	}
				
		// 카카오페이 주문
		public int kakaopay_OrderRegister(OrderProductandCartList orderProductandCartList) {
			// 총 합산가격, 합산포인트를 저장할 변수
//			int totalPrice = 0;
//			int totalSavePoint = 0;
//			int deliveryCost = 0;
//			
//			// 주문상품정보, 장바구니정보, 배송주소를 가져온다.
//			List<OrderProduct> opList = orderProductandCartList.getOrderProductList();
//			List<Cart> cartList = orderProductandCartList.getCartList();
//			String delivery_address = orderProductandCartList.getDelivery_address();
//			
//			// 주문할 상품목록을 순회하면서 총 합산가격, 합산포인트를 구한다.
//			// 주문한 상품의 재고를 차감한다.
//			for(int i=0; i<opList.size(); i++) {
//				totalPrice += opList.get(i).getPrice();
//				totalSavePoint += opList.get(i).getSavepoint();
//						
//			// 재고를 차감할 상품의 정보를 받아온다.
//			Product reduceProduct = orderDao.product(opList.get(i).getProduct_id());
//			
//			
//			
//			// 주문한 상품수량만큼 차감한다.
//			reduceProduct.setStock(reduceProduct.getStock() - opList.get(i).getQuantity());
//			
//			
//			// DB에 있는 Product 테이블을 갱신한다.
//			orderDao.update(reduceProduct);
//			
//			
//			
//			
//			}
//			
//			// 총 상품 주문금액이 5만원 이상일 경우 배송비 무료 아닐 경우 3000원
//			if(totalPrice >= 50000) {
//				deliveryCost = 0;				
//			}
//			else {
//				deliveryCost = 3000;
//			}
//			
//			// 잘 저장했나 체크
//			
//			
//			// 결제할 유저 정보를 가져온다.
//			User user = orderDao.user(orderProductandCartList.getCartList().get(0).getUser_id());
//			
//			// 카카오페이의 경우 일반결제와는 달리 유저의 잔액을 차감시킬 필요가 없으니 포인트만 가감산처리 하면 된다.
//			
//			
//			
//			
//			user.setPoint(user.getPoint() - orderProductandCartList.getUsedPoint() + totalSavePoint);
//			
//			
//			
//			
//			// 주문 정보, 주문 상품 정보 , 배송정보 생성
//			
//			int ordersInfoCheck = 0;
//			int orderProductCheck = 0;
//			
//			for(int i=0; i<opList.size(); i++) {
//				// opList(주문할 상품 정보)의 정보를 토대로 주문정보를 생성
//				Orders orders = new Orders();
//				
//				String orderId = createOrderId();
//				
//				orders.setOrder_id(orderId);
//				orders.setStatus("배송준비");
//				orders.setPayment_method("카카오페이");
//				orders.setTotal_amount(opList.get(i).getQuantity());
//				orders.setUser_id(cartList.get(0).getUser_id());
//				orders.setDelivery_cost(deliveryCost);
//				orders.setDelivery_address(delivery_address);
//				
//				// 주문정보와 주문상품 정보 등록
//				ordersInfoCheck = orderDao.register(orders);
//				
//				orderProductCheck = orderProductRegister(orderId, opList.get(i));
//			
//				if(ordersInfoCheck == 1 && orderProductCheck == 1) {
//					
//					
//				}
//						
//			}
//			
//			// 장바구니에서 온 요청일 경우에만 지운다.
//			if(orderProductandCartList.isCartCheck()) {
//				// 장바구니에 담겨있던 상품정보를 지운다.
//				for(int i=0; i<cartList.size(); i++) {
//					orderDao.delete(cartList.get(i));
//				}
//			}
//					
//			// 정상적으로 처리됐을 경우 1을 반환
//			   if(ordersInfoCheck == 1 && orderProductCheck == 1)
//				   return 1;
		   
		    // 잘 안됐을 경우엔 0 반환한다.
		   	   return 0;
			
		}
		
		// 구매이력 목록 가져오기
		@Override
		public List<Purchase_History> get_Purchase_History(Map map) {
			return orderDao.purchase_History(map);
		}
		
		// 구매횟수 가져오기
		@Override
		public int get_Purchase_Count(String user_id) {
			return orderDao.purchase_Count(user_id);
		}

		// 유저정보 받아오기
		@Override
		public User getUserInfo(String user_id) {
			return orderDao.user(user_id);
		}
		
		@Override
		public int registerOrders(int product_id, String id, int quantity) {
			// TODO Auto-generated method stub
			return 0;
		}
}
