package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch01")
public class Ch01Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);

	@RequestMapping("/content") // http://localhost:8080 가 앞에 생략 이후 + "/ch01/home"로 검색
	public String content() {
		logger.info("실행");
		return "ch01/content"; // 뷰(JSP)의 파일명
	}
} 
