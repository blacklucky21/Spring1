package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
	
	private Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	
	public void test() {
		logger.error("error 로그");
		logger.warn("error 로그");
		logger.info("error 로그");
		logger.debug("error 로그");
		logger.trace("error 로그");
		
	}
	
	public static void main(String[] args) {
		new Log4jTest().test();
	}

}
