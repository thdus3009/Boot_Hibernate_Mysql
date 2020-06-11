package com.sy.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sy.s1.util.Pager;

@SpringBootTest
class NoticeServiceTest {

	private NoticeService noticeService;
	
	void boardListTest()throws Exception {
		Pager pager = new Pager();
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num");
		List<NoticeVO> ar = noticeService.getselectList(pager);
		
		for(NoticeVO noticeVO : ar) {
			System.out.println("num : "+ noticeVO.getNum());
			System.out.println("num : "+noticeVO.getTitle());
		}
	}
}
