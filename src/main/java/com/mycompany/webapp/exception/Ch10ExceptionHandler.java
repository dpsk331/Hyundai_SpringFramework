package com.mycompany.webapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component // 객체로 생성해서 관리하도록 설정하는, 해당 클래스를 가지고 미리 객체를 생성시켜 놓고 관리하라는 의미의 어노테이션
@ControllerAdvice // 모든 컨트롤러에 영향을 미치는 설정, 컨트롤러가 발생해야 도움을 주는 !!
public class Ch10ExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(Ch10ExceptionHandler.class);
	
	// @Component의 자동 객체 실행 확인을 위한 기본 생성자 실행 확인 과정임!
	public Ch10ExceptionHandler() {
		logger.info("실행");
	}
	
	// 예외 처리자 설정
	@ExceptionHandler
	public String handleNullPointerException(NullPointerException e) {
		logger.info("실행");
		e.printStackTrace();
		return "error/500_null";
	}
	
	@ExceptionHandler
	public String handleClassCastException(ClassCastException e) { // 타입 변환이 잘못될 시에 발생하는 예외
		logger.info("실행");
		e.printStackTrace();
		return "error/500_cast";
	}
	
	// 전체적인 에러에 대한 예외 처리
	@ExceptionHandler
	public String handleException(Exception e) { // try-catch를 하지 않고 잡을 수 있는 에러는 RuntimeException뿐!?
		logger.info("실행");
		e.printStackTrace();
		return "error/500";
	}
	
	@ExceptionHandler
	public String handleException(Ch10SoldOutException e) { // try-catch를 하지 않고 잡을 수 있는 에러는 RuntimeException뿐!?
		logger.info("실행");
		e.printStackTrace();
		return "error/soldout";
	}
}
