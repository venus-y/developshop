package com.first.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.first.shop.dto.QandA;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class QuestionBoardDaoImplTest {
	@Autowired
	QuestionBoardDao questionBoardDao;
	
	
	// 질문 작성
	@Test
	public void testQuestion() {
		for(int i=0; i<90; i++) {
			QandA qandA = new QandA();
			qandA.setContent("test");
			qandA.setInquirytype("배송관련");
			qandA.setTitle("테스트제목");
			qandA.setWriter("geumsung7769");
		
			questionBoardDao.question(qandA);
		}
		
	}
	
	// 글 목록 가져오기
	@Test
	public void getList() {
		
		Map map = new HashMap();
		map.put("offset", 0);
		map.put("pageSize", 10);
		
		
		List<QandA> list = questionBoardDao.list(map);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	// 글 정보 가져오기
	@Test
	public void getInfo() {
		QandA qandA = questionBoardDao.forReply(1);
		
		System.out.println(qandA);
	}
	
	// 답글 작성
	@Test
	public void reply() {
		QandA qandA = new QandA();
		qandA.setContent("text");
		qandA.setInquirytype("배송관련");
		qandA.setTitle("TEST");
		qandA.setWriter("tester");
		qandA.setPbno(2);
		
		questionBoardDao.reply(qandA);
	}
	
	// 답글 개수를 알아온다 -> pbno 카운트를 통해
	@Test
	public void checkReply() {
		int count = questionBoardDao.pbnoCount(1);
		
		System.out.println("count: " + count);
	}

	// 답글에 다시 답글 작성 시 부모글번호를 최초글의 글번호로 지정
	@Test
	public void originalPbno() {
		int pbno = questionBoardDao.originalPbno(1);
		
		System.out.println(pbno);
	}
	
	// 질문글 총 개수 가져오기
	@Test
	public void getAllCnt() {
		int cnt = questionBoardDao.count();
		System.out.println(cnt);
	}
}
