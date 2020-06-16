package com.sy.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.sy.s1.board.notice.NoticeVO;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackFor= Exception.class)
public class QnaService implements BoardService{

	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;

	@Value("${board.qna.filePath}")//key값
	private String filepath;//appllication.properties참고
	
	//update (첨부파일은 놔두고 나머지 내용만 수정)
	public QnaVO boardUpdate(QnaVO qnaVO, MultipartFile [] files)throws Exception {
		
		qnaRepository.qnaUpdate(qnaVO.getTitle(), qnaVO.getContents(), qnaVO.getNum());
		
		return qnaVO;
	}
	
	//delete
	public boolean boardDelete(QnaVO qnaVO)throws Exception {
		qnaRepository.deleteById(qnaVO.getNum());
		return qnaRepository.existsById(qnaVO.getNum());
	}

	public Page<QnaVO> boardList(Pager pager)throws Exception {
		
		// return qnaRepository.findByNumGreaterThanOrderByNumDesc(0);

		System.out.println(qnaRepository.count());
		
		pager.makeRow();

		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(), //한페이지당 몇개씩 보여줄건지
				Sort.by("ref").descending().and(Sort.by("step").ascending()));//ref로 정렬, desc / step으로 asc

		Page<QnaVO> page = null;

		if (pager.getKind() == null || pager.getKind().equals("title")) {
			//page안에 totalPage가 들어있음
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);

		} else if (pager.getKind().equals("contents")) {

			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);

		} else {

			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);

		}

		pager.makePage(page.getTotalPages());
		
		return page;
	}
	
	//"원본글"
	public QnaVO boardWrite(QnaVO qnaVO, MultipartFile [] files)throws Exception {
		//ref = 자기자신의 글번호
		//step, dept 0
		
//		qnaVO.getRef();
//		qnaVO.getDepth();
//		qnaVO.getStep();
//		qnaVO.getHit();
		
		List<QnaFileVO> list = new ArrayList<>();
		
		//저장할 폴더 경로
		File file = filePathGenerator.getUserResouceLoader(filepath);
		
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<1) {
				continue;
			}
			QnaFileVO qnaFileVO = new QnaFileVO();
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			
			list.add(qnaFileVO);
		}
		
		qnaVO.setBoardFiles(list);
		
		//
		qnaVO = qnaRepository.save(qnaVO);
		
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO); //save는 "update"기능도 있음
	}
	
	//"답글" // 글번호로 먼저 부모의 정보를 찾아와야함
	public QnaVO boardReply(QnaVO qnaVO)throws Exception {

		QnaVO childVO = new QnaVO(); //부모의 정보 옮겨놓기
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());

		//update
		//ref 부모의 ref와 같고 step이 부모의 step보다 큰것들
		//step 1씩 증가
		
		//1.부모의 정보 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(qnaVO.getRef(), qnaVO.getStep());

		for(QnaVO q : ar) {
			q.setStep(q.getStep()+1);
		}

		qnaRepository.saveAll(ar);
		
		//자기자신의 ref는 부모의 ref
		//자기자신의 step은 부모의 step+1
		//자기자신의 depth는 부모의 depth+1
		
		childVO.setRef(qnaVO.getRef());//부모의 ref를 자식 ref에 넣어준다.
		childVO.setStep(qnaVO.getStep()+1);
		childVO.setDepth(qnaVO.getDepth()+1);
		
		qnaRepository.save(childVO); //save : primary키로 조회가 된다면 -update , 조회가 안된다면-insert
		
		
		return childVO;
	}
	
	//selectOne
	//hit(조회수) - 매개변수로 번호를 받거나 QnaVO로 받기
	public QnaVO getSelectOne(QnaVO qnaVO) throws Exception {
		//조회수
		//qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		qnaVO = qnaRepository.qnaSelect(qnaVO.getNum());
		qnaVO.setHit(qnaVO.getHit()+1);
		
		//selectOne
		Optional<QnaVO> opt = qnaRepository.findById(qnaVO.getNum());
		qnaVO = opt.get();
		
		return qnaVO;
	}
	
}
