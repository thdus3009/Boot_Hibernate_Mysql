package com.sy.s1.board.notice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sy.s1.board.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="notice")
public class NoticeVO extends BoardVO {

	
	//notice하나당 noticeFile여러개, 현재위치가 notice이기때문에 OneToMany(양방향)
	
	@OneToMany(mappedBy = "noticeVO" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)//부모가 지워지면 같이 지워진다.(cascade)
	private List<NoticeFileVO> noticeFileVOs;
	
	//FetchType : eager - notice조회할때 noticefile도 같이 조회
	//			: lazy - notice조회할때 오직 notice만 조회하고 , 쓰려고 선언해야지만 noticeFile을 조회할수있다.


}
