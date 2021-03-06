package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Service("bService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao bDAO;

	@Override
	public int getListCount() {
		// TODO Auto-generated method stub
		return bDAO.getListCount();
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		
		
		return bDAO.selectList(pi);
	}

	@Override
	public int insertBoard(Board b) {
		
		return bDAO.insertBoard(b);
	}

	@Override
	public void addReadCount(int bId) {
		
		bDAO.addReadCount(bId);
		
	}

	@Override
	public Board selectBoard(int bId) {
		return bDAO.selectBoard(bId);
	}

	@Override
	public int updateBoard(Board b) {
		// TODO Auto-generated method stub
		return bDAO.updateBoard(b);
	}

	@Override
	public int deleteBoard(int bId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectTopList() {
		// TODO Auto-generated method stub
		return bDAO.selectTopList();
	}

	@Override
	public ArrayList<Reply> getReplyList(int bId) {
		// TODO Auto-generated method stub
		return bDAO.getReplyList(bId);
	}

	@Override
	public int insertReply(Reply r) {
		// TODO Auto-generated method stub
		return bDAO.inesrtReply(r);
	}



}
