package com.sy.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	private NoticeVO noticeVO;
	private List<NoticeFileVO> noticeFileVOs;
	
	@BeforeEach //각 테스트 메서드 실행전에 한번씩 실행하는 것
	public void beforeEach() {
		noticeVO = new NoticeVO();
		noticeVO.setTitle("title");
		noticeVO.setWriter("writer");
		noticeVO.setContents("contents");
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName");
		noticeFileVO.setOriName("oriName");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("secondFileName");
		noticeFileVO.setOriName("secondOriName");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		noticeVO.setNoticeFileVOs(noticeFileVOs);
	}
	
	//insert
	//@Test
	public void insertTest()throws Exception{
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
	//update
	//@Test
	public void updateTest()throws Exception {
		noticeVO.setNum(1L);
		noticeVO.setTitle("update Title");
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
	//delete
	//@Test
	public void deleteTest()throws Exception {
		noticeRepository.deleteById(2L); //long 타입이라서
		boolean check = noticeRepository.existsById(2L); //존재하는지 아이디(PK)로 찾기
		
		assertNotEquals(false, check);
	}
	
	//selectList - 갯수가 0이 아닌지 확인
	//@Test
	public void selectListTest() throws Exception{
		List<NoticeVO> ar = noticeRepository.findAll();
		for(NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getTitle());
		}
		
		assertNotEquals(0, ar.size());
	}

	//selectOne //이거할 때 noticeVo fetch타입이 eager이여야 한다.
	//@Test
	public void selectOneTest() throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(6L); 
		
		//if(opt.isPresent()) //데이터가 있는지 확인
		//Optional > null체크(NullPointException 방지)
			
		noticeVO = opt.get(); //Optional에서 꺼내야함
		
		System.out.println("Select One Test");
		System.out.println(noticeVO.getRegDate());
		System.out.println(noticeVO.getNoticeFileVOs().get(0).getFileName()); //0번째 인덱스에 있는 fileName
		assertNotNull(noticeVO);
	}
	
	
	//@Test
	public void customTest() {
		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0L);
		for(NoticeVO noticeVO:ar) {
			System.out.println("Num : "+noticeVO.getNum());
		}
	}
	
	
	//@Test
	public void customTest2() {
		List<NoticeVO> ar = noticeRepository.findByNumBetween(6, 10);
		for(NoticeVO noticeVO:ar) {
			System.out.println("Num : "+noticeVO.getNum());
		}
	}
	
	@Test
	public void insertTest2()throws Exception {
		for (int i=0; i<10; i++) {
			noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setContents("contents"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO = noticeRepository.save(noticeVO);
		}
		
	}
	
	
	
}
