package com.kh.spring.memo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.common.interceptor.LoginInterceptor;
import com.kh.spring.memo.model.service.MemoService;

@Controller
public class MemoController {
	
	
	//private Logger logger = LoggerFactory.getLogger(MemoController.class);
		@Autowired
		MemoService mmService;
		
		@RequestMapping("memo.do")
		public ModelAndView memo() {
			
			//logger.debug("[Before]MemoController-memo()");
			ArrayList<HashMap<String,String>> list = mmService.selectMemoList();
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("list",list);
			mv.setViewName("memo/memo");
			
			//logger.debug("[After]MemoController - memo()");
			
			return mv;
		}
		
		@RequestMapping("memo/insertMemo.do")
		public String insertMemo(@RequestParam("memo") String memo,String password) {
			//logger.debug("[Before]MemoController-insertMemo()");

			System.out.println("저기요");
			HashMap<String,String> memoJson = new HashMap<String,String>();
			memoJson.put("memo",memo);
			memoJson.put("password",password);
			
			mmService.insertMemo(memoJson);
		
				//logger.debug("[After]MemoController -insertMemo()");
			
		
			return "redirect:/memo.do";
		}
		

		
		@RequestMapping("deleteMemo.do")
		public String deleteMemo(String no,String password) {
			
			HashMap<String,String> memoJson = new HashMap<String,String>();
			memoJson.put("no",no);
			memoJson.put("password",password);
			
			mmService.deleteMemo(memoJson);
			
			return "redirect:/memo.do";
		}
		
		
		

}
