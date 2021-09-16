package com.mycompany.webapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class Ch15Aspect8Around {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect8Around.class);
	
	@Around("execution(public * com.mycompany.webapp.controller.Ch15Controller.board*(..))")
	public Object loginCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		String mid = (String)session.getAttribute("sessionMid");
		if(mid == null) { //로그인이 되어있을 경우
			request.setAttribute("loginCheck", false); //(이름, 값)
		} else {
			request.setAttribute("loginCheck", true);
		}
		//로그인이 되어있지 않을 경우
		Object result = joinPoint.proceed(); //비즈니스 로직 호출
		return result; //후처리		
	}
}
