package com.kh.spring.memo.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

public interface MemoService {

	ArrayList<HashMap<String, String>> selectMemoList();

	

	void insertMemo(HashMap<String, String> memoJson);



	int deleteMemo(HashMap<String, String> memoJson);




}
