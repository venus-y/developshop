package com.first.shop.service;

import java.util.List;
import java.util.Map;

import com.first.shop.dto.QandA;

public interface QuestionBoardService {
	int register_Question(QandA qandA);
	
	List<QandA> get_QuestionList(Map map);
	
	QandA get_QuestionInfo(int bno);
	
	QandA get_forReplyInfo(int pbno);
	
	int register_Reply(QandA qandA);
	
	int get_AllCount();
}
