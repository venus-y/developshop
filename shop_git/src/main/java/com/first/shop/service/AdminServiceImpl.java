package com.first.shop.service;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.first.shop.dao.AdminDao;
import com.first.shop.dao.OrderDao;
import com.first.shop.dto.Category;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.ProductImage;
import com.first.shop.dto.User;

@Service
public class AdminServiceImpl implements AdminService {
	
//	@Autowired
	private final AdminDao adminDao;	
	private final OrderDao orderDao;
	private final JavaMailSender javaMailSender;
	
	
	public AdminServiceImpl(AdminDao adminDao, OrderDao orderDao, JavaMailSender javaMailSender) {
		this.adminDao = adminDao;
		this.orderDao = orderDao;
		this.javaMailSender = javaMailSender;
	}
		
	@Override
	// 상품 등록
	public int registerProduct(Product product) {
		return adminDao.register(product);
	}

	@Override
	// 카테고리 목록 불러오기
	public List<Category> getCategoryList() {
		return adminDao.list();
	}

	@Override
	// 상품 이미지 등록
	public int registerProductImage(ProductImage image) {
		return adminDao.registerImg(image);
	}
	
	// 상품 정보 수정
	@Override
	public int updateProductInfo(Product product) {
		return adminDao.update(product);
	}

	// 상품 정보 삭제
	@Override
	public int deleteProductInfo(int product_id) {
		return adminDao.delete(product_id);
	}
	
	// 주문정보 목록 가져오기
	@Override
	public List<Orders> getOrderInfoList() {
		return adminDao.orderInfoList();
	}
	
	// 배송정보 등록하기
	@Override
	public int registerDeliveryInfo(Orders orders) {
		// 뷰로부터 넘겨받은 orders의 주문번호로 db에 있는 주문정보를 조회해온다.
		// 배송상태를 바꿔줘야 하기 때문
		Orders updateOrders = adminDao.orderInfo(orders);
		// 배송상태를 배송중으로 변경한다.
		updateOrders.setStatus("배송중");
		adminDao.updateOrders(updateOrders);		
		// 배송상품을 받을 유저의 정보를 가져온다.
		User receiver = orderDao.user(updateOrders.getUser_id());
		
		// 운송장 정보를 등록한다(주문번호, 배송지, 운송장번호, 배송사번호 정보를 추가)
		DeliveryInfo deliveryInfo = new DeliveryInfo();
		deliveryInfo.setOrder_id(updateOrders.getOrder_id());
		deliveryInfo.setShipping_address(receiver.getAddress());
		deliveryInfo.setCompany_id(1);		
		// 랜덤한 12자리의 운송장번호를 생성
		Random random = new Random();
		int track1 = random.nextInt(888888)+111111;
		int track2 = random.nextInt(888888)+111111;
		String trackNum = track1 + "-" + track2;		
		deliveryInfo.setTracking_number(trackNum);
		deliveryInfo.setCompany_name("xx운송");
		// 배송정보를 DB에 등록
		adminDao.deliveryInfo(deliveryInfo);
		// 배송정보가 등록됐단 메일을 발송한다.
		// 발신자
				String setFrom = "geumsung7769@naver.com";
				// 수신자
				String toMail = receiver.getEmail();
				// 제목 및 내용
				String title =  receiver.getName() + "님, 구매하신 상품에 대한 배송정보입니다.";
				String content = 
						"저희 상품을 구매해주셔서 감사합니다." +
						"<br><br>" +
						"주문번호는 " + deliveryInfo.getOrder_id() + " 입니다." +
						"<br>" +
						"운송장번호는 " + deliveryInfo.getTracking_number() + " 입니다." +
						"<br>" +
						"감사합니다.";
				
				// 전송하는 메일 형식 설정
				try {
					  MimeMessage message = javaMailSender.createMimeMessage();
					  MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
					  helper.setFrom(setFrom);
					  helper.setTo(toMail);
					  helper.setSubject(title);
					  helper.setText(content,true);
					  javaMailSender.send(message);
				  } catch(Exception e) {
					  e.printStackTrace();
				  }	
				
				  // 여기까지 잘 됐을 경우 1 반환
				  return 1;
	}

}
