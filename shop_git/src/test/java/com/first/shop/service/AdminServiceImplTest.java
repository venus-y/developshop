package com.first.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class AdminServiceImplTest {
	
	@Autowired
	AdminService adminService;
	@Autowired
	ProductService productService;
	
	@Test
	public void testRegisterProduct() {
		// 서비스에서 넘겨준 상품객체가 DB까지 잘 등록되는지 확인
Product product = new Product();
		
		product.setProduct_name("테스트");
		product.setProduct_name("상품");
		product.setPrice(5000);
		product.setDiscount(10);
		product.setStock(10);
		product.setCategory_code(1);
		product.setSavepoint(10);
		
		// 행의 개수가 1일 반환되면 잘 등록됨
		int rowcnt = adminService.registerProduct(product);
		if(rowcnt == 1) {
			System.out.println("상품이 잘 등록됐습니다.");
		} else {
			System.out.println("상품 등록 실패");
		}
	}
	
	@Test
	public void testUpdate() {
		Product product = productService.getProductInfo(61);
		product.setProduct_name("테스터1223금성");
		adminService.updateProductInfo(product);
	}
	
	@Test
	public void testDelete() {
		Product product = productService.getProductInfo(245);
		adminService.deleteProductInfo(product);
	}

}
