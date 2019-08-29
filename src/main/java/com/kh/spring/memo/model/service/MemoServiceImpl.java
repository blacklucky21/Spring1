package com.kh.spring.memo.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.common.interceptor.LoginInterceptor;
import com.kh.spring.memo.model.dao.MemoDAO;



@Service("mmService")
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	MemoDAO mmDAO;

	
	//private Logger logger = LoggerFactory.getLogger(MemoServiceImpl.class);
	
	
	@Override
	public ArrayList<HashMap<String, String>> selectMemoList() {
		//logger.debug("[Before]MemoServiceImpl - selectMemoList()");
		
		ArrayList<HashMap<String,String>> list = mmDAO.selectMemoList();
		//logger.debug("[After]MemoServiceImpl - selectMemoList()");
		return list;
		
		
			//return mmDAO.selectMemoList();
	}





	@Override
	public void insertMemo(HashMap<String, String> memoJson) {
		//logger.debug("[Before]MemoServiceImpl - insertMemo()");
		
			mmDAO.insertMemo(memoJson);
			
			//logger.debug("[After]MemoServiceImpl - insertMemo()");
	}





	@Override
	public int deleteMemo(HashMap<String, String> memoJson) {
		
		
		return mmDAO.deleteMemo(memoJson);
		
	}







}
