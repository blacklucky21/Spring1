package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;


@Repository("mDAO")
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession; // root-context.xml에서 작성한 bean으로 생성

	public Member selectMember(Member m) {
		
		return (Member)sqlSession.selectOne("memberMapper.selectOne",m);
	}

}
