package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect_ {
	
	private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
//	@Pointcut("execution(* com.kh.spring.memo..*(..))")
//	public void myPointcut() {}
//	
//	@Around("myPointcut()") 3줄로쓰기
	@Around("execution(* com.kh.spring.memo..*(..))") // 1줄로 쓰기
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		/******** Target Object에 대한 정보 추출 ********/
		Signature signature = joinPoint.getSignature();
		// signature : 반환값가 method를 불러온다 ModelAndView com.kh.spring.memo.controller.MemoController.memo();
		
		logger.debug("signature = "+signature);
		
		String type = signature.getDeclaringTypeName();
		
		//type : com.kh.spring.memo.controller.MemoController
		
		logger.debug("type = " + type);
		
		String methodName = signature.getName();
		
		logger.debug("methodName = "+ methodName);
		
		
		/******** Target Object에 대한 정보 추출 끝 ********/
		
		String componentName ="";
		if(type.indexOf("Controller")>-1) {
			
			componentName = "Controller : ";
			
		}else if(type.indexOf("service") > -1) {
			componentName = "ServiceImp : ";
		}else if (type.indexOf("DAO")>-1) {
			componentName = "DAO :";
		}
		
//		logger.debug("[Before]"+componentName + type + "."+methodName+"()");
//		
//		return joinPoint.proceed();
		
		logger.debug("[Before]"+componentName + type + "."+methodName+"()");
		Object obj = joinPoint.proceed();
		logger.debug("[After]"+componentName + type + "."+methodName+"()");
		return obj;
	}

}
