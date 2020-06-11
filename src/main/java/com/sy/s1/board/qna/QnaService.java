package com.sy.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardService;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackFor= Exception.class)
public class QnaService implements BoardService{

	@Autowired
	private QnaRepository qnaRepository;
	

	public Page<QnaVO> boardList(Pager pager)throws Exception {
		
		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> page=null;
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		
		return page;
	}
	
	public QnaVO boardWrite(QnaVO qnaVO)throws Exception {
		//원본글
		//ref = 자기자신의 글번호
		//step, dept 0

		
		qnaVO = qnaRepository.save(qnaVO);
		
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO); //save는 update기능도 있음
	}

	
	
	
}
