package com.first.shop.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
			System.out.println(phList.get(i));
		}
	}
}
