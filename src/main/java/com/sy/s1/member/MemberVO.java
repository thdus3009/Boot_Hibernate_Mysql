package com.sy.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data 					// getter, setter, toString .. 다 만들어 주는것 (lombok라이브러리중 하나)
@Entity
@Table(name="member")   //member라는 테이블과 MemberVO와 맵핑시킨다는 의미 (create table member)
						//즉, insert into member (?,?,?...) //?표에 해당되는 것은 VO와 Repository로 결정된다.
@DynamicUpdate //데이터가 있는것만 업데이트를 하고 없는것은 이전것을 그대로 쓰자는 의미의 Annotation
public class MemberVO {
	@Id				//primary key 역할
	@NotEmpty
	private String id;
	
	@Column 		//그냥 일반 컬럼명이라는 의미
	@NotEmpty
	@Size(min = 4, max = 10)
	private String pw;
	
	@Transient		//table에서 제외하라는 의미 (테이블에 넣을것이 아니라는 뜻)
	private String pwCheck;
	
	@Column
	private String name;
	
	@Column
	@Email
	private String email;
	
	@Column
	private String phone;

	//foreign key 연결작업 (MemberVO, MemberFileVO)
	//mappedBy="Join하는 Entity에 선언된 '자기 자신의 Entity 변수명'을 쓴다."
	@OneToOne(mappedBy = "memberVO" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)//부모가 지워지면 같이 지워진다.(cascade)
	private MemberFileVO memberFileVO;
}
