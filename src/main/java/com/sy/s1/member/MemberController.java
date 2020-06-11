package com.sy.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**/")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberLogin")
	public void memberLogin()throws Exception {
		
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(ModelAndView mv, MemberVO memberVO, HttpSession session)throws Exception {
		
		memberVO = memberService.memberLogin(memberVO);
		
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("result", "Login Fail");
			mv.addObject("path", "memberLogin");
			mv.setViewName("common/result");
		}
		return mv;
	}
	
	@GetMapping("memberLogOut")
	public String memberLogOut(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}
	
	//--------------------------------------------------------------------------
	
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin(ModelAndView mv, MemberVO memberVO) throws Exception {
		mv.setViewName("member/memberJoin");
		mv.addObject("memberVO", memberVO); // form error쓰려면 이거 추가

		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(ModelAndView mv, @Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile files)
			throws Exception {

		boolean result1 = memberService.memberError(memberVO, bindingResult);

		if (result1) {// 에러==true
		
			mv.setViewName("member/memberJoin");
		
		} else { //nullpointexception 뜰 확률을 없애기 위해서 else안에 넣어준다.

			memberVO = memberService.memberJoin(memberVO, files);

			if (memberVO!=null) {
				mv.addObject("result", "Join Success");
				mv.addObject("path", "../");
				mv.setViewName("common/result");
			} else {
				mv.addObject("result", "Join Fail");
				mv.addObject("path", "memberJoin");
				mv.setViewName("common/result");
			}
			
		}

		return mv;
	}
	
	
	//--------------------------------------------------------------------------
	
	@GetMapping("memberMyPage")
	public void memberMyPage()throws Exception {
		
	}
	 
	//--------------------------------------------------------------------------
	
	@GetMapping("memberDelete")
	public ModelAndView memberDelete(HttpSession session, ModelAndView mv, MemberVO memberVO)throws Exception {
		memberVO =(MemberVO)session.getAttribute("member");
		memberService.memberDelete(memberVO);
		
		session.invalidate();
		mv.addObject("result", "계정이 삭제되었습니다.");
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		return mv;
	}
	
	@PostMapping("memberUpdate")
	public void memberUpdate()throws Exception {
		
	}
	
}
