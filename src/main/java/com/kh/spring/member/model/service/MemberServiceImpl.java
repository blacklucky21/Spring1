package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.Member;

@Service("mService")
public class MemberServiceImpl implements MemberService{

	
	@Autowired
	private MemberDAO mDAO;
	
	
	
	@Override
	public Member memberLogin(Member m) {
		
		
		return mDAO.selectMember(m);
		

	}

	@Override
	public int insertMember(Member m) {
		
		return mDAO.insertMember(m);
	}

	@Override
	public int updateMember(Member m) {
		
		return mDAO.updateMember(m);
	}

	@Override
	public int updatePwdMember(Member m) {
		
		return mDAO.updatePwdMember(m);
	}

	@Override
	public int deleteMember(String id) {
	
		return mDAO.deleteMember(id);
	}
	

	
	

}
