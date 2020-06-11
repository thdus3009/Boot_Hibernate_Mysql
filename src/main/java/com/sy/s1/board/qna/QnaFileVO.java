package com.sy.s1.board.qna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="qnaFile")
public class QnaFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //1씩 증가
	private Long fileNum;

	//join해주는 num은 빼야한다.
	
	@Column(nullable = false)
	private String fileName;
	@Column
	private String oriName;
	
	
	@ManyToOne
	@JoinColumn(name="num")
	private QnaVO qnaVO;
}
