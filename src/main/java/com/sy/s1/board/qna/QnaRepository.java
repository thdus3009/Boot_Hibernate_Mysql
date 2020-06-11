package com.sy.s1.board.qna;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QnaRepository extends JpaRepository<QnaVO, Long> {

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
