package com.first.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.dto.QandA;
import com.first.shop.dto.QandAPageHandler;
import com.first.shop.service.QuestionBoardService;

@RequestMapping("/question")
@Controller
public class QuestionBoardController {
	
	private final QuestionBoardService questionBoardService;
	
	public QuestionBoardController(QuestionBoardService questionBoardService) {
		this.questionBoardService = questionBoardService;
	}
	
	// 작성 페이지 불러오기
	@GetMapping("/getWrite")
	public String getWrite() {
		return "questionForm";
	}
	
	// 질문글 작성
	@PostMapping("/postWrite")
	public String postWrite(QandA qandA) {
		
		questionBoardService.register_Question(qandA);
		// 원래는 게시글 목록으로 와야함
		return "redirect:/";
	}
	
	// 글목록 가져오기
	@GetMapping("/getList")
	public String getList(Integer page, Integer pageSize, Model model) {
		// 처음 페이지 진입시 page, pageSize 값 설정이 안돼있으므로 기본값으로 설정
		if(page == null && pageSize == null) {
			page = 1;
			pageSize = 10;
		}
		// 질문글 개수를 가져온다.
		int totalCount = questionBoardService.get_AllCount();
		
		// 페이지 정보 생성
		QandAPageHandler ph = new QandAPageHandler(page, pageSize, totalCount);
		
		// 오프셋정보와 페이지사이즈 정보를 맵에 담아 넘긴다.
		Map map = new HashMap();
		map.put("offset", (ph.getPage()-1)*pageSize);
		map.put("pageSize", ph.getPageSize());
		
		// DB로부터 작성된 질문 게시글목록을 가져온다.
		List<QandA> questionList = questionBoardService.get_QuestionList(map);
		model.addAttribute("questionList", questionList);
		model.addAttribute("ph", ph);
		
		return "questionList";
	}
	
	// 글정보 가져오기
	@GetMapping("/getInfo")
	public String getInfo(int bno ,Model model) {
		// DB로부터 글정보를 받아옴
		QandA qanda = questionBoardService.get_QuestionInfo(bno);		
		model.addAttribute("qanda", qanda);
		
		return "questionInfo";
	}
	
	// 답글 작성 페이지
	@GetMapping("/getReply")
	public String getReply(int pbno, Model model) {
		// 답글 작성 페이지로 이동
		// 원글정보를 넘겨준다.
		QandA qanda = questionBoardService.get_forReplyInfo(pbno);
		model.addAttribute("qanda", qanda);
		return "replyForm";
	}
	
	// 답글 등록
	@PostMapping("/postReply")
	public String postReply(QandA qandA) {
		
		
		questionBoardService.register_Reply(qandA);
		return "redirect:/question/getList";
	}
}
