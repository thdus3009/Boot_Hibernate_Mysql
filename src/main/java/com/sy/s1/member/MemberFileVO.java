package com.sy.s1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

//getter setter
@Data
@Table(name="memberFile") //db에 저장시키고 싶은 테이블이름
@Entity //table을 따라간다.
public class MemberFileVO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//자동증가(auto-increment) 
	//mysql은 auto-increment oracle은 시퀀스사용
	private long fileNum;
	
//  -FK (Foreign Key)를 가지고 있는 테이블을 Owner(관계의 주인)라고 부른다.
//	-Owner VO 내에서는 FK의 멤버변수를 생략한다. > 대신, MemberVO에다가 MemberFileVO를 선언+@OneToOne 어노테이션을 쓴다.
	//id지우기
	
	@Column//(name으로 이름도 바꿀 수 있다.)
	private String fileName;
	
	@Column
	private String oriName;
	
	@OneToOne
	@JoinColumn(name="id")
	private MemberVO memberVO;
	
}
