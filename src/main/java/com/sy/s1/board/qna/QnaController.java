package com.sy.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	//list
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager)throws Exception{ 
		//Pageable: spring내의 page클래스
		//@PageableDefault 선언해서 page의 설정을 해준다.
		//size: 한 페이지당 몇개의 정보를 출력할건지    //page : 몇페이지부터 시작할건지(0이 1페이지)
		//sort: 무엇을 기준으로 desc(내림차순)해줄건지
		
		ModelAndView mv = new ModelAndView();

		Page<QnaVO> page = qnaService.boardList(pager);
		
		System.out.println(page.getContent().size());
		System.out.println("한페이지에 몇개의 정보를 출력하는지 : "+page.getSize());
		System.out.println("TotalElements(글의 갯수) : "+page.getTotalElements());
		System.out.println("TotalPages(페이지 갯수) : "+page.getTotalPages());
		System.out.println("Next(다음페이지가 존재하는지 확인) : "+page.hasNext());
		System.out.println("Previous(이전페이지가 존재하는지 확인) : "+page.hasPrevious());
		System.out.println("number(page번호) : "+page.getNumber()); //page.number() == page.getNumber()
		System.out.println("조회했을때 list가 있는지없는지(조회한 결과물) : "+page.hasContent());
		System.out.println("First(첫페이지인지) : "+page.isFirst());
		System.out.println("Last(마지막페이지인지) : "+page.isLast());		
		
		mv.addObject("page", page);
		mv.setViewName("board/boardList2");
		//page꾸려주는 방식은  notice방법(boardList)과 qna방법(boardList2) 중 선택해서 하면된다.
		return mv;
	}
	
	
	//write
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO", new QnaVO());
		mv.addObject("path", "Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	//ref를 다시 글번호로 대체 (update)
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(ModelAndView mv,QnaVO qnaVO) throws Exception {
		qnaVO.setHit(0L);
		qnaVO = qnaService.boardWrite(qnaVO);
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
}
