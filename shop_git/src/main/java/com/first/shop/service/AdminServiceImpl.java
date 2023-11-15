package com.first.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.shop.dao.AdminDao;
import com.first.shop.dto.Category;
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

}
