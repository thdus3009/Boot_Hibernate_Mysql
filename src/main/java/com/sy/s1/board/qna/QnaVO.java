package com.sy.s1.board.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sy.s1.board.BoardVO;
import com.sy.s1.board.notice.NoticeFileVO;
import com.sy.s1.board.notice.NoticeVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="qna")
public class QnaVO extends BoardVO{

	//이미 테이블이 만들어져 있으면 @Column 안써도 된다.
	//어노테이션줘서 하는 것은 개발용, 개인용일때 사용 추천
	@Column
	private Long ref;
	@Column
	private Long step;
	@Column
	private Long depth;
	
	@OneToMany(mappedBy = "qnaVO" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)//부모가 지워지면 같이 지워진다.(cascade)
	private List<QnaFileVO> qnaFileVOs;
	
}
