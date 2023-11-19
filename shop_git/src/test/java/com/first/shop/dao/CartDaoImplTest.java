package com.first.shop.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.Cart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartDaoImplTest {
	
	@Autowired
	CartDao cartDao;
	
	
	// 장바구니 등록 테스트
	@Test
	public void testRegister() {
		// 잘 등록되면 1 아니면 0 반환
		Cart cart = new Cart();
		cart.setProduct_id(230);
		cart.setUser_id("geumsung7769");
		cart.setQuantity(3);
		
		int rowCnt = cartDao.register(cart);
		
		if(rowCnt == 1) {
			System.out.println("장바구니가 등록됐습니다!");
		}else {
			System.out.println("등록 실패");
		}
	}
	
	// 장바구니 수정
	@Test
	public void testUpdate() {
		Cart cart = new Cart();
		cart.setCart_id(27);
		cart.setUser_id("geumsung7769");
		cart.setQuantity(10);
		
		cartDao.update(cart);
		
		
	}
}
