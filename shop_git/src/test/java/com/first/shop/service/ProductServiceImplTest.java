package com.first.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.PageHandler;
import com.first.shop.dto.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductServiceImplTest {
	@Autowired
	ProductService productService;
	
	@Test
	public void testGetProductList() {
		List<Product> list = productService.getProductList();
	
		// 리스트가 반환되면 성공
		if(list != null) { 
			System.out.println("조회 성공");
			System.out.println(list);
		}else {
			System.out.println("조회 실패");
		}
	}
	
	
	@Test
	public void testCnt() {
		PageHandler ph = new PageHandler(1, 4, 8);
		
		Map map = new HashMap();
		map.put("offset", (ph.getPage()-1)*ph.getPageSize());
		map.put("pageSize", ph.getPageSize());
		
		List<Product> list = productService.getPageList(map);
		
//		System.out.println(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(i + "번째"  + "+" + list.get(i));
		}
	}
	
	@Test
	public void getProductInfo() {
		Product product = productService.getProductInfo(60);
		if(product!=null) {
			System.out.println("상품을 찾았습니다.");
			System.out.println(product);
		}else {
			System.out.println("상품이 존재하지 않습니다.");
		}
		
		
	}
	
}
