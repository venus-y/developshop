package com.first.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.shop.dao.AdminDao;
import com.first.shop.dto.Category;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.ProductImage;

@Service
public class AdminServiceImpl implements AdminService {
	
//	@Autowired
	private final AdminDao adminDao;
	
	// 생성자 주입을 권장하길래 처음 사용 쓰는 의미를 좀 더 알아볼 필요는 있음.
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
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
		여기서부터 다시 하면 된다!
		
		return 0;
	}

}
