package com.first.shop.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.shop.dao.OrderDao;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	
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
	
		
		
		return 1;
	}

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
