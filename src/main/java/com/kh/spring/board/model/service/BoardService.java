package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

public interface BoardService {
	
	int getListCount(); //전체 갯수를 가져옴
	
	ArrayList<Board> selectList(PageInfo pi);
	
	//보드삽입
	int insertBoard(Board b);
	//조회수 증가
	void addReadCount(int bId);
	//게시물 디테일 가져오기
	Board selectBoard(int bId);
	
	int updateBoard(Board b);
	
	int deleteBoard(int bId);

	ArrayList<Board> selectTopList();


	
	

}
