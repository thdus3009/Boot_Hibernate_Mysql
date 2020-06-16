package com.sy.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.s1.member.MemberVO;

@Component
public class MemberCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			//현재 세션에 있는 member정보를 obj에 담고 (로그인을 안했으면 null값이다.) 
			//null이면 못들어가고(false) null이 아니면 들어갈수있음(true)
				
			MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
			//Object obj로 받아와서 써도된다. (그럼 형변환 안해줘도 됨)
				

			if(memberVO != null) {
				System.out.println("Member");
				return true;
			}else {
				System.out.println("로그인이 필요");
				response.sendRedirect("./message/messageResult?result=Please Sign in to see this contents.&path=../qnaList");
			//response.sendRedirect("./qnaList");		//controller에 작성

					
				return false;
			}

		}
}
