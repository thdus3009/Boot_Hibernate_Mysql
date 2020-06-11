package com.sy.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, String> { //VO , primary key의 데이터타입

	//내장 메서드
	//save() : insert
	//쿼리문 대신 메서드가 제공
	//메서드를 통해서 쿼리문이 자동으로 생성
	
	//기본 제공 메서드 
	// S save(T) : insert, update (MemberService 참고)
	// <S extends T> S save (Interable<? extends T>) : 다중 insert, update (MemberRepositoryTest참고)
	
	// void deleteById(ID) : PK를 통해 삭제하는것
	// void delete(T) : 주어진 Entity 삭제
	// void deleteAll(Interable<? extends T>) : 주어진 모든 Entity
	// void deleteAll : 모든 Entity 삭제
	
	// List<T> findAll() : 모든 Entity를 조회(select)한다.
	// Optional<T> findById(ID(PK)) : PK로 단일 Entity 조회(select)
	// long count() : Entity의 갯수
	// boolean existsById(ID) : PK로 Entity 존재 여부
	
	//---------------------------------------------------------------------------
	
	//id와 pw를 받을때 (커스텀 메서드 / 사용자가 만드는 메서드 / 쿼리 메서드)
	//참고 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
	
	//public abstract ...
	//select * from member where id=? and pw=? 같은 경우
	public MemberVO findByIdAndPw(String id, String pw); //변수명과상관없이 순서에 따라 맵핑한다.

	//쿼리 메서드 (사용자가 메서드를 만들어야할 경우)이면 여기에 선언한다.
	
}
