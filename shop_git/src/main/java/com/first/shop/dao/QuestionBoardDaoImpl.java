package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.QandA;

@Repository
public class QuestionBoardDaoImpl implements QuestionBoardDao {
	
	String namespace = "com.first.shop.dao.QuestionBoardDao.";
	
	private final SqlSession session;
	
	public QuestionBoardDaoImpl(SqlSession session) { 
		this.session = session;
	}
		
	// 질문게시판 글 작성
	@Override
	public int question(QandA qandA) {
		session.insert(namespace+"register_Question", qandA);
		// 새로 등록된 게시글의 pbno 값을 bno로 설정해준다.
		return setPbno();
	}
	
	// 질문게시글 총 개수 가져오기
	@Override
	public int count() {
		return session.selectOne(namespace+"get_AllCount");
	}
	
	// 글 목록 가져오기
	@Override
	public List<QandA> list(Map map) {
		return session.selectList(namespace+"get_QuestionList", map);
	}

	// 글 정보 가져오기
	@Override
	public QandA info(int bno) {
		return session.selectOne(namespace+"get_QuestionInfo", bno);
	}

	// 답글 작성
	@Override
	public int reply(QandA qandA) {
		return session.insert(namespace+"register_Reply", qandA);
	}

	// 답글 개수 체크
	@Override
	public int pbnoCount(int pbno) {
		return session.selectOne(namespace+"check_ReplyCount", pbno);
	}

	// 추가 답글 작성시 최초글의 글번호를 부모번호로 설정
	@Override
	public int originalPbno(int pbno) {
		return session.selectOne(namespace+"get_OriginalPbno", pbno);
	}
	
	// 답글을 위한 정보
	@Override
	public QandA forReply(int pbno) {
		return session.selectOne(namespace+"get_forReplyInfo", pbno);
	}
	
	// 새로 등록된 게시글의 pbno 값을 bno로 설정해준다.
	@Override
	public int setPbno() {
		return session.update(namespace+"set_Pbno");
	}
	
	
	
	
}
