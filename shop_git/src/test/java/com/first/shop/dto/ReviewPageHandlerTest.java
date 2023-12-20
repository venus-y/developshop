package com.first.shop.dto;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class ReviewPageHandlerTest {

	@Test
	public void testReviewPageHandler() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaging() {
		ReviewPageHandler rph = new ReviewPageHandler(55, 5, 5);		
		System.out.println(rph);
	}

}
