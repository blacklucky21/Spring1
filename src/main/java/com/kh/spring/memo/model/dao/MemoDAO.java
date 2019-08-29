package com.kh.spring.memo.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.memo.model.service.MemoServiceImpl;

@Repository("mmDAO")
public class MemoDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	//private Logger logger = LoggerFactory.getLogger(MemoDAO.class);
	public ArrayList<HashMap<String, String>> selectMemoList() {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("memoMapper.selectMemoList");
	}



	public void insertMemo(HashMap<String, String> memoJson) {
		// TODO Auto-generated method stub
		//logger.debug("[Before]MemoServiceImpl - insertMemo()");
		 sqlSession.insert("memoMapper.insertMemo",memoJson);
		// logger.debug("[After]MemoServiceImpl - insertMemo()");
	}



	public int deleteMemo(HashMap<String, String> memoJson) {
		// TODO Auto-generated method stub
		return sqlSession.delete("memoMapper.deleteMemo",memoJson);
	}
	


}
