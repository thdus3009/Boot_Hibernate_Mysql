package com.sy.s1.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //매개변수 없는 생성자
@AllArgsConstructor //매개변수가 있는 생성자

@MappedSuperclass
//다른 테이블의 부모 역할한다고 Annotation으로 선언해주어야함 (상속용도)
public class BoardVO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long num;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	//@CreationTimestamp //만들때 시간을 자동으로 넣기
	@UpdateTimestamp
	private Date regDate;
	@Column
	private long hit;
	
	
	
}
