package com.sy.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	// select 한번 날려서 없으면 insert하고 있으면 update

	@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("choa1");
		memberVO.setPw("choa");
		memberVO.setName("choa");
		memberVO.setEmail("choa@naver.com");
		memberVO.setPhone("01011112222");

		MemberVO memberVO2 = new MemberVO();
		memberVO2.setId("iu1");
		memberVO2.setPw("iu");
		memberVO2.setName("iu");
		memberVO2.setEmail("iu4405@naver.com");
		memberVO2.setPhone("01077024405");

		List<MemberVO> ar = new ArrayList<>();
		ar.add(memberVO);
		ar.add(memberVO2);

	// Iterable (Iterator과 Iterable의 차이점)
		ar = memberRepository.saveAll(ar);
		assertNotNull(memberVO);
	}
	
	
	
//	@Test
//	void idcheck() {//중복된 아이디 검사 (해당아이디가 있으면 true)
//		boolean check = memberRepository.existsById("iu1"); //exists: memberVO //existsById: boolean
//		System.out.println(check);
//	}
	
	
	
	// update //id로 email 바꾸기
//	@Test void updateTest() { 
//	MemberVO memberVO = new MemberVO();
//		  memberVO.setId("id"); 
//		  memberVO.setEmail("thdus3009@naver.com");
//		  
//		  memberVO = memberRepository.save(memberVO); 
//		  //email이 없으면 insert 있으면 update된다./ 수정안되는 것까지 다 넣어주지 않으면 나머지부분에 null값이 들어가게 된다. 
//		  //그러므로 id중복검사를 철저히 해야한다.(기존아이디에 새롭게 다른아이디로 update될 수 있으므로) 
//		  assertNotNull(memberVO); 
//	 }

	
	
	// 마지막 2명만 delete하기
//
//	@Test
//	void deleteTest() {
//		MemberVO memberVO = new MemberVO();
//		MemberVO memberVO2 = new MemberVO();
//		memberVO.setId("id");
//		memberVO2.setId("sy");
//
//		List<MemberVO> ar = new ArrayList<>();
//		ar.add(memberVO);
//		ar.add(memberVO2);
//		memberRepository.deleteAll(ar);
//	}
	
	//id와 pw로 조회 (select * from member where id=? and pw=? 같은 경우)
//	@Test
//	void loginTest() {
//		MemberVO memberVO = memberRepository.findByIdAndPw("bb", "bbbb"); //아이디, 비번
//		assertNotNull(memberVO);
//	}
		
		
	

}
