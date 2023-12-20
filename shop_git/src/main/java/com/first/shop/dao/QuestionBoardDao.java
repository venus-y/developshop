package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.QandA;

public interface QuestionBoardDao {
	int question(QandA qandA);

	List<QandA> list(Map map);
	
	QandA info(int bno);
	
	QandA forReply(int pbno);
	
	int reply(QandA qandA);
	
	int pbnoCount(int pbno);
	
	int originalPbno(int pbno);
	
	int count();
	
	int setPbno();
} 
