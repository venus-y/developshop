package com.first.shop.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminDaoImplTest {
	
	@Autowired 
	AdminDao adminDao;
	@Autowired
	ProductDao productDao;
	
	// 상품등록 테스트
	@Test
	public void testRegister_product() {
		Product product = new Product();
		
		product.setProduct_name("테스트");
		product.setProduct_name("상품");
		product.setPrice(5000);
		product.setDiscount(10);
		product.setStock(10);
		product.setCategory_code(1);
		product.setSavepoint(10);
		
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
	
	// 상품 삭제
	@Test
	public void deleteProduct() {
		Product product = productDao.productInfo(244);
		adminDao.delete(product);
		
	}
	
	

}
