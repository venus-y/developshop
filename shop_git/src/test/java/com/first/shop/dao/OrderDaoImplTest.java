package com.first.shop.dao;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.OrderInfoPageHandler;
import com.first.shop.dto.Purchase_History;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderDaoImplTest {
	@Autowired
	OrderDao orderDao;
	
	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProduct() {
		fail("Not yet implemented");
	}
	
	//구매이력 가져오기
	@Test
	public void get_Purchase_History() {
		List<Purchase_History> phList = orderDao.purchase_History("geumsung7769");

		for(int i=0; i<phList.size(); i++) {
			
		}
	}
	
	//구매횟수 가져오기
	@Test
	public void get_Purchase_Count() {
		int count = orderDao.purchase_Count("geumsung7769");
		
		OrderInfoPageHandler ph = new OrderInfoPageHandler(1, 5, count);
		
		
		
		Map map = new HashMap();
		
		map.put("offset", (ph.getPage()-1)*ph.getPageSize());
		map.put("pageSize", ph.getPageSize());
		map.put("user_id", "geumsung7769");
		
		List<Purchase_History> list = orderDao.purchase_History(map);
		
		for(int i=0; i<list.size(); i++) {
			
		}
		
		
		
		
		
	}
}
