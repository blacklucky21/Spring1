package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {
   // Log를 넣는 Aspect
   
   private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
   
   public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
      
      /***** Target Object에 대한 정보 추출 시작 *****/
      Signature signature = joinPoint.getSignature();
      // signature : ModelAndView com.kh.spring.memo.controller.MemoController.memo() ==> 예시
      logger.debug("signature : " + signature); // 메모 탭을 눌렀을때
      
      String type = signature.getDeclaringTypeName();
      // type : com.kh.spring.memo.controller.MemoController
      logger.debug("type : " + type);
      
      String methodName = signature.getName();
      // methodName : memo()
      logger.debug("methodName : " + methodName);
      /***** Target Object에 대한 정보 추출 끝 *****/
      
      String commponentName = "";
      if(type.indexOf("Controller") > -1) {
         commponentName = "Controller : "; 
      }else if (type.indexOf("Service") > -1) {
         commponentName = "ServiceImpl : ";
      }else if (type.indexOf("DAO") > -1) {
         commponentName = "DAO : ";
      }
      
//      logger.debug("[Before]" + commponentName + type + ", " + methodName + "()");
//      
//      return joinPoint.proceed();
      
      logger.debug("[Before]" + commponentName + type + ", " + methodName + "()");
      Object obj = joinPoint.proceed();
      logger.debug("[After]" + commponentName + type + ", " + methodName + "()");
      return obj;
   }
}