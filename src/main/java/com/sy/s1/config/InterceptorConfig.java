package com.sy.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sy.s1.interceptor.MemberCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private MemberCheckInterceptor memberCheckInterceptor;
	
	@Override//고리처럼 연결되어 있음
	public void addInterceptors(InterceptorRegistry registry){
		
		//회원
		registry.addInterceptor(memberCheckInterceptor)
		.addPathPatterns("/qna/*")
		.excludePathPatterns("/qna/qnaList");
		
		//list를 제외한 모든 qna관련 내용은 memberCheckInterceptor의 영향을 받는다.
	}
	
}
