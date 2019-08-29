package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.member.model.vo.Member;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			logger.info("비 로그인 상태에서 ["+request.getRequestURI()+"]에 접근 하려고 함");
			
			request.setAttribute("msg", "게시판 기능의 대부분은 로그인을 하셔야 이용하실 수 있습니다.");
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
			
			
			return false;
			
			
		}

		
		return super.preHandle(request, response, handler);
	}

}
