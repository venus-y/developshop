package com.first.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.Category;
import com.first.shop.dto.PageHandler;
import com.first.shop.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductDaoImplTest {
	@Autowired
	ProductDao productDao;
	
	@Test
	public void testList() {
		List<Product> list = productDao.list();
		
		System.out.println(list);
	}
	
	@Test
	// 상품 개수 조회
	public void cnt() {
		int prCnt = productDao.count();
		System.out.println(prCnt);
	}
	
	
	@Test
	//페이지 테스트
	public void pageTest() {
		PageHandler ph = new PageHandler();
		int totalCnt = productDao.count();		
		ph.Paging(1, 2, totalCnt);
//		System.out.println(ph);
		
		// 상품 페이지 정보를 가져오기 위해 필요한 값들을 map에 담아 넘겨준다.
		Map map = new HashMap();
		// 
		map.put("offset", (ph.getPage()-1)*ph.getPageSize());
		map.put("pageSize", ph.getPageSize());
		
		List<Product> list = productDao.pageList(map);
		System.out.println(list);	
	}
	
	//상품 정보 조회
	@Test
	public void getProduct() {
		Product product = productDao.productInfo(55);
		if(product != null) {
			System.out.println("조회 성공");
			System.out.println(product);
		}else {
			System.out.println("상품이 존재하지 않습니다.");
		}
		
		
	}
	
	
	//카테고리별 상품 조회
	@Test
	public void getCategorySet() {		
		Map map = new HashMap();
		map.put("pageSize", 8);
		map.put("offset", 0);
		map.put("category_code", 11);
		
		List<Product> list = productDao.categorySet(map);
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(i+"번째");
			System.out.println(list.get(i));
			System.out.println("------------------------");
		}		
	}
	
	//브랜드별 분류
	@Test
	public void getBrandSort() {
		List<Category> list = productDao.sortBrand();
		
		System.out.println(list);
	}
	
	// 브랜드 상품 목록 가져오기
	@Test
	public void getBrandPage() {
		List<Category> cList = productDao.getBrand(1);
		System.out.println(cList);
	}
	
	
	
}
