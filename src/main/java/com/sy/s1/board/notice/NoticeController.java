package com.sy.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.s1.board.BoardVO;
import com.sy.s1.member.MemberVO;
import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeWrite")
	public ModelAndView setInsert(ModelAndView mv, HttpSession session)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setWriter(memberVO.getId());
		
		
		mv.addObject("path", "Write");
		mv.addObject("boardVO", noticeVO);
		
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(NoticeVO noticeVO, MultipartFile [] files) throws Exception {
		ModelAndView mv = new ModelAndView();
		noticeVO = noticeService.setInsert(noticeVO, files);
		if(noticeVO!=null) {
			mv.setViewName("redirect:noticeList");
		}else {
			System.out.println("작성 실패");
		}
		return mv;
	}
	
	@GetMapping("noticeList")
	public ModelAndView getselectList(Pager pager)throws Exception{
	//@PageableDefault(page = 0, size = 10, sort = {"num"}, direction = Direction.DESC ) Pageable pageable, @RequestParam(defaultValue = "") String search	
		
		ModelAndView mv = new ModelAndView();
		//									(page, size, Sort, culumn)
		//Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num");
		Page<NoticeVO> ar = noticeService.getselectList(pager);
		
		mv.addObject("page", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList2");
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(ModelAndView mv, NoticeVO noticeVO)throws Exception {
		noticeVO = noticeService.getSelectOne(noticeVO);
		mv.addObject("vo", noticeVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
}
