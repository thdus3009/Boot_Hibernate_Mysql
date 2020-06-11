package com.sy.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sy.s1.board.notice.NoticeRepository;
import com.sy.s1.board.notice.NoticeVO;

@SpringBootTest
class MemberRepositoryTest2 {

	@Autowired
	private MemberRepository memberRepository;

	// select 한번 날려서 없으면 insert하고 있으면 update

//	@Test
//	void insertTest() {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId("choa1");
//		memberVO.setPw("choa");
//		memberVO.setName("choa");
//		memberVO.setEmail("choa@naver.com");
//		memberVO.setPhone("01011112222");
//
//		MemberFileVO memberFileVO = new MemberFileVO();
//		memberFileVO.setFileName("fileName");
//		memberFileVO.setOriName("oriName");
//
//		memberVO.setMemberFileVO(memberFileVO);
//
//		memberRepository.save(memberVO);
//	}

//	@Test
	void insertTest2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("choa2");
		memberVO.setPw("choa");
		memberVO.setName("choa");
		memberVO.setEmail("choa@naver.com");
		memberVO.setPhone("01011112222");

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");

		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		memberRepository.save(memberVO);
	}

//	@Test
	void loginTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("choa1");
		memberVO.setPw("choa");
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw()); // 아이디, 비번
		System.out.println(memberVO.getMemberFileVO().getFileName());
		System.out.println(memberVO.getMemberFileVO().getOriName());
		assertNotNull(memberVO);
	}

	
	//@Test
	void updateTest2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("suji2");
		memberVO.setPw("suji2"); // 검증으로 비밀번호4자리이상을 걸어놔서 자꾸 에러 발생했던것.
		memberVO.setEmail("suji22@naver.com");
		
		memberVO = memberRepository.save(memberVO);
		assertNotNull(memberVO);
	}	
	
	//@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("suji2");
		memberVO.setPw("suji2"); // 검증으로 비밀번호4자리이상을 걸어놔서 자꾸 에러 발생했던것.
		memberVO.setEmail("suji22@naver.com");

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("change file name");
		memberFileVO.setOriName("change ori name");
		
		memberFileVO.setFileNum(6);
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberVO = memberRepository.save(memberVO);
		assertNotNull(memberVO);
	}
	
//	@Test
	void deleteTest() { 
		memberRepository.deleteById("choa1");
	}
	


}
