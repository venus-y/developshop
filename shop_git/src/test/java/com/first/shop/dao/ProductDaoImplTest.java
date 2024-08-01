package com.first.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.BrandSearchCondition;
import com.first.shop.dto.Category;
import com.first.shop.dto.CategorySearchCondition;
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
		
		
	}
	
	@Test
	// 상품 개수 조회
	public void cnt() {
		int prCnt = productDao.count();
		
	}
	
	
	@Test
	//페이지 테스트
	public void pageTest() {
		PageHandler ph = new PageHandler();
		int totalCnt = productDao.count();		
		ph.Paging(1, 2, totalCnt);
//		
		
		// 상품 페이지 정보를 가져오기 위해 필요한 값들을 map에 담아 넘겨준다.
		Map map = new HashMap();
		// 
		map.put("offset", (ph.getPage()-1)*ph.getPageSize());
		map.put("pageSize", ph.getPageSize());
		
		List<Product> list = productDao.pageList(map);
			
	}
	
	//상품 정보 조회
	@Test
	public void getProduct() {
		Product product = productDao.productInfo(55);
		if(product != null) {
			
			
		}else {
			
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
			
			
			
		}		
	}
	
	//브랜드별 분류
	@Test
	public void getBrandSort() {
		List<Category> list = productDao.sortBrand();
		
		
	}
	
	// 브랜드 상품 목록 가져오기
	@Test
	public void getBrandPage() {
		
		Map map = new HashMap();
		map.put("category_code", 1);
		map.put("pageSize", 8);
		map.put("offset", 0);
		
		List<Product> PList = productDao.getBrand(map);
		
	}
	
	// 브랜드 상품 총 개수 가져오기
	@Test
	public void getBrandProductCount() {
		int count = productDao.categoryCount(1);
		
				
	}
	
	// 브랜드 상품 검색 조건 테스트
	@Test
	public void searchTest() {
		BrandSearchCondition brandSearchCondition = new BrandSearchCondition(1, 8, "바지", 2);
		
		List<Product> pList = productDao.getBrand_Search(brandSearchCondition); 
		
		for(int i=0; i<8; i++) {
			
		}
	}
	
	// 카테고리별 분류 조건 테스트
	@Test
	public void cSearchTest() {
		CategorySearchCondition csc = new CategorySearchCondition(1, 8, "청바지", 2);
		
		int pCount = productDao.categoryCount(csc);
		
		
	}
	
	
}
