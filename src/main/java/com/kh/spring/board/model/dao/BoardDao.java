package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Repository("bDAO")
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int getListCount() {
		return sqlSession.selectOne("boardMapper.getListCount");
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		
		int offset = ((pi.getCurrentPage()-1 ) * pi.getBoardLimit()); // 해당페이지에서 뛰어넘을 게시글수 측정
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("boardMapper.getSelectList",null,rowBounds);
	}

	public int insertBoard(Board b) {
		
		return sqlSession.insert("boardMapper.insertBoard",b);
	}

	public void addReadCount(int bId) {
		sqlSession.update("boardMapper.updateCount",bId);
		
	}

	public Board selectBoard(int bId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}

	public int updateBoard(Board b) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateBoard",b);
	}

	public ArrayList<Board> selectTopList() {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("boardMapper.selectTopList");
	}

	public ArrayList<Reply> getReplyList(int bId) {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList",bId);
	}

	public int inesrtReply(Reply r) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertReply",r);
	}
	

}
