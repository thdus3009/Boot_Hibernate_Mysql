package com.sy.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardService;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;

@Service
@Transactional(rollbackFor= Exception.class)
public class QnaService implements BoardService{

	@Autowired
	private QnaRepository qnaRepository;
	

	public Page<QnaVO> boardList(Pageable pageable, String search, String kind)throws Exception {
		
		Page<QnaVO> page = qnaRepository.findByTitleContaining(search, pageable);
		
		if(kind==null) {
			page = qnaRepository.findAll(pageable);
		}else {
			
			if(kind.equals("title")) {
				page= qnaRepository.findByTitleContaining(search, pageable);
			}else if(kind.equals("writer")) {
				page= qnaRepository.findByWriterContaining(search, pageable);
			}else {
				page= qnaRepository.findByContentsContaining(search, pageable);
			}
			
		}
		
		return qnaRepository.findAll(pageable);
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
