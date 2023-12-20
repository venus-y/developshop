package com.first.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.first.shop.dao.QuestionBoardDao;
import com.first.shop.dto.QandA;

@Service
public class QuestionBoardServiceImpl implements QuestionBoardService {

	private QuestionBoardDao questionBoardDao;
	
	QuestionBoardServiceImpl(QuestionBoardDao questionBoardDao){
		this.questionBoardDao = questionBoardDao;
	}
	
	// 질문게시판 글 작성
	@Override
	public int register_Question(QandA qandA) {
		// 답글 등록, 게시글 조회 시 pbno를 기준으로 데이터를 처리한다.
		// pbno 값이 null일 경우엔 bno값으로 pbno를 설정한다.
		/*
		 * if(qandA.getPbno()) { qandA.setPbno(qandA.getBno()); }
		 */
		
		return questionBoardDao.question(qandA);
	}
	
	// 글 목록 가져오기
	@Override
	public List<QandA> get_QuestionList(Map map) {
		return questionBoardDao.list(map);
	}
	
	// 총 질문글수 가져오기
	@Override
	public int get_AllCount() {
		return questionBoardDao.count();
	}
	
	// 글 정보 가져오기
	@Override
	public QandA get_QuestionInfo(int bno) {
		return questionBoardDao.info(bno);
	}
	
	// 답글 작성에 필요한 정보 가져오기
	@Override
	public QandA get_forReplyInfo(int pbno) {
		QandA forReply = questionBoardDao.forReply(pbno);
//		// 게시글에 답글이 하나 이상 달렸는지 확인
//		int replyCount = questionBoardDao.pbnoCount(pbno);
//		// 이미 하나가 달렸을 경우 이후에 달리는 답글의 pbno를 최초글의 bno로 설정한다.
//		if(replyCount > 1) {
//			int originalPbno = questionBoardDao.originalPbno(pbno);
//			forReply.setPbno(originalPbno);
//		}
		
		return forReply;
	}
	
	// 답글 작성
	@Override
	public int register_Reply(QandA qandA) {
		return questionBoardDao.reply(qandA);
	}
	
	

	

}
