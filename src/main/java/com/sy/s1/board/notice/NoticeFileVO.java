package com.sy.s1.board.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sy.s1.member.MemberFileVO;
import com.sy.s1.member.MemberVO;

import lombok.Data;

//이 vo만드는 것 자체가 테이블을 생성하는 것이다.

@Data
@Table(name="noticeFile") //db에 저장시키고 싶은 테이블이름
@Entity //table을 따라간다.
public class NoticeFileVO {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//자동증가(auto-increment) 
	//mysql은 auto-increment oracle은 시퀀스사용
	private long fileNum;
	
	
//  -FK (Foreign Key)를 가지고 있는 테이블을 Owner(관계의 주인)라고 부른다.
//	-Owner VO 내에서는 FK의 멤버변수를 생략한다. > 대신, MemberVO에다가 MemberFileVO를 선언+@OneToOne 어노테이션을 쓴다.
	//id지우기
	@ManyToOne //noticefile은 여러개 notice는 하나인데, 현재위치가 noticefile이기때문에 ManyToOne(양방향)
	@JoinColumn(name="num") //글번호로 조인한다.
	private NoticeVO noticeVO;
	
	
	@Column(nullable = false)//not null 설정
	private String fileName;
	
	@Column
	private String oriName;
	

}
