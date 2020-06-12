package com.sy.s1.board.qna;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface QnaRepository extends JpaRepository<QnaVO, Long> {

	//select JPQL이용 - *쓰면 안된다. / Q는 별칭
	@Query("select Q from QnaVO Q where Q.num=?1")
	QnaVO qnaSelect(Long num);
	
	//전체조회가 아닌경우
	@Query("select Q.title, Q.writer from QnaVO Q where Q.num=?1")
	Object[] qnaSelect2(Long num);
	
	
	
	
	//update JPQL이용 - (JPQL은 쿼리를 따로 입력하기 때문에 메서드명이 내마음대로 입력해도 된다.)
	@Transactional
	@Modifying
	@Query(value = "update QnaVO q set q.title=?1, q.contents=?2 where q.num=?3") //3번인덱스
	void qnaUpdate(String title, String contents, Long num); 
	
	@Transactional
	@Modifying
	@Query(value = "update QnaVO q set q.title=:title, q.contents=:contents where q.num=:num") //3번인덱스
	void qnaUpdate2(String title, String contents, Long num); 
	
	
	
	
	//답글 (ref를 찾는데 step이 더 큰것)
	List<QnaVO> findByRefAndStepGreaterThan(long ref, long step);
	
	public int countByTitleContaining(String search);
	public int countByWriterContaining(String search);
	public int countByContentsContaining(String search);
	
	//title검색
	Page<QnaVO> findByTitleContaining(String search, Pageable pageable);
	
	//Contents검색
	Page<QnaVO> findByContentsContaining(String search, Pageable pageable);
	
	//Writer검색
	Page<QnaVO> findByWriterContaining(String search, Pageable pageable);
	
}
