package com.sy.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {

	//select > findBy컬럼명 조건..
	
	
	//Containg %1% (찾는내용의 갯수)
	public int countByTitleContaining(String search);
	public int countByWriterContaining(String search);
	public int countByContentsContaining(String search);
	
	//찾는 내용들(조회)
	public List<NoticeVO> findByTitleContaining(String search, Pageable pageable);
	public List<NoticeVO> findByWriterContaining(String search, Pageable pageable);
	public List<NoticeVO> findByContentsContaining(String search, Pageable pageable);
	//세가지 한꺼번에 검색하기 (findByTitleContainingOrWriterContainingOrContentsContaining)
	
	
	//참고: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
	
	//select * from notice where num>0 order by num desc;
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);
	
	//select * from notice where num between 6 and 10 / 매개변수로 받아주어야함
	public List<NoticeVO> findByNumBetween(long n1, long n2);
	
	//select * from notice where title like '1' order by num desc > 타이틀이 무조건 1
	public List<NoticeVO> findByTitleLikeOrderByNumDesc(String search);
	
	// 1이 들어가는 전체조회
	//select * from notice where title like '%1%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);

	//검색 & 페이징 처리 & 조회는 최신순(num이 큰순서)
	
	
}
