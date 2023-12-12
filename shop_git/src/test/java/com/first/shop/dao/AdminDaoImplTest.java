package com.first.shop.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminDaoImplTest {
	
	@Autowired 
	AdminDao adminDao;
	@Autowired
	ProductDao productDao;	
	@Autowired
	OrderDao orderDao;
	
	// 상품 테스트용으로 많이 등록
	@Test
	public void registerTest() {
		for(int i=0; i<50; i++) {
			testRegister_product();
		}
	}
	
	// 상품등록 테스트
	@Test
	public void testRegister_product() {
		Product product = new Product();
		
		product.setProduct_name("테스트청바지");
		product.setPrice(50000);
		product.setDiscount(10);
		product.setStock(10);
		product.setCategory_code(222);
		product.setSavepoint(1000);
		product.setProduct_image("\\2023\\12\\10\\0476341b-18da-4590-ad51-219508f7409b_다운로드 (13).jpg");
		product.setProduct_thumbimage("\\2023\\12\\10\\s\\s_0476341b-18da-4590-ad51-219508f7409b_다운로드 (13).jpg");
		
		// 행의 개수가 1일 반환되면 잘 등록됨
		int rowcnt = adminDao.register(product);
		if(rowcnt == 1) {
			System.out.println("상품이 잘 등록됐습니다.");
		} else {
			System.out.println("상품 등록 실패");
		}
	}
	
	@Test
	public void testUpdate() {
		Product product = productDao.productInfo(52);
		System.out.println(product);
		product.setProduct_name("테스트12");
		adminDao.update(product);
		
		
	}
	
	
	@Test
	public void testRegisterImg() {
		// 반환된 행의 개수가 1이면 성공
		Product product = new Product();
		product.setProduct_id(1);
		product.setProduct_name("Example Product");
		product.setPrice(10000);
		product.setDiscount(5);
		// release_Date는 Date 객체로 설정
		product.setRelease_date(new Date());
		product.setStock(50);
		product.setCategory_code(123);
		product.setSavepoint(10);
		product.setProduct_image("example_product.jpg");
		product.setProduct_thumbimage("example_thumb_product.jpg");
		
		int rowCnt = adminDao.register(product);	
		System.out.println("결과:"+rowCnt);
	}
	
	//  배송정보 등록 테스트
	@Test
	public void testRegisterDelivery(){
		DeliveryInfo deliveryInfo = new DeliveryInfo();
		deliveryInfo.setCompany_id(1);
		deliveryInfo.setOrder_id("20231202_13209913-236d-48ac-82c3-e0f898be3a00");
		deliveryInfo.setShipping_address("test");
		deliveryInfo.setTracking_number("33");
		
		adminDao.deliveryInfo(deliveryInfo);
	}
	
	@Test
	public void testGetOrderInfo() {
		Orders orders = new Orders();
		orders.setOrder_id("20231202_13209913-236d-48ac-82c3-e0f898be3a00");
		Orders orders2 = adminDao.orderInfo(orders);
		System.out.println(orders2);
		
	}
	
	// 주문정보 변경
	@Test
	public void updateOrders() {
		Orders orders = new Orders();
		orders.setOrder_id("20231202_85cf0698-8223-4fe2-a0f7-5a78df55d8da");
		
		Orders updateOrder = adminDao.orderInfo(orders);
		updateOrder.setStatus("배송중테스트");
		
		adminDao.updateOrders(updateOrder);
	}
	
}
