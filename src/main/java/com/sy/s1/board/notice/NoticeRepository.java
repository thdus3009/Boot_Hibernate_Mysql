package com.sy.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {

	//select
	//findBy컬러명 조건 ....
	
	
	//title 검색
	Page<NoticeVO> findByTitleContaining(String search, Pageable pageable);
		
		//contents 검색
	Page<NoticeVO> findByContentsContaining(String search, Pageable pageable);
		
		//writer 검색
	Page<NoticeVO> findByWriterContaining(String search, Pageable pageable);
	
	
	
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);
	
	//select * from notice where num between 6 and 10
	public List<NoticeVO> findByNumBetween(long n1, long n2);
	
	//select * from notice where title like '%1%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);
	
	
}
