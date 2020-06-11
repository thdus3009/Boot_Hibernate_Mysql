package com.sy.s1.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sy.s1.board.notice.NoticeVO;

@SpringBootTest
class QnaRepositoryTest {

	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private QnaService qnaService;
	
	//@Test
	public void insertTest2() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setTitle("title2");
		qnaVO.setContents("contents2");
		qnaVO.setWriter("writer2");
		
		qnaVO = qnaService.boardWrite(qnaVO);
		assertNotNull(qnaVO);
		
	}
	
	
	@Test
	public void insertTest3()throws Exception {
		for (int i=0; i<100; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title"+i);
			qnaVO.setContents("contents"+i);
			qnaVO.setWriter("writer"+i);
			qnaVO = qnaRepository.save(qnaVO);
		}
		
	}
	
	
	//@Test
	public void insertTest() {
		
		QnaVO qnaVO = new QnaVO();
		qnaVO.setTitle("title");
		qnaVO.setContents("contents");
		qnaVO.setWriter("writer");
		
		qnaVO = qnaRepository.save(qnaVO);
		assertNotNull(qnaVO);
	}
	
}
