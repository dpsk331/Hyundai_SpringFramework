package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch03/content";
	}
	
//	// 매개변수명은 파라미터명과 동일해야 함!
//	@GetMapping("/method1")
//	public String method1( 
//			@RequestParam("param1") String p1, // 실제 파라미터명과 매개변수명이 다를 경우 이런 식으로 사용
//			String param2,
//			String param3,
//			String param4,
//			String param5
//	) {
//		logger.info("실행");
//		logger.info("param1: " + p1);
//		logger.info("param2: " + param2);
//		logger.info("param3: " + param3);
//		logger.info("param4: " + param4);
//		logger.info("param5: " + param5);
//		return "redirect:/ch03/content";
//	}
	
//		@GetMapping("/method1")
//		public String method1(
//				String param1, 
//				@RequestParam(defaultValue="0") int param2,
//				@RequestParam(defaultValue="0.0") double param3,
//				@RequestParam(defaultValue="false") boolean param4,
//				@DateTimeFormat(pattern="yyyy-MM-dd") Date param5
//		) {
//			logger.info("실행");
//			logger.info("param1: " + param1);
//			logger.info("param2: " + param2);
//			logger.info("param3: " + param3);
//			logger.info("param4: " + param4);
//			logger.info("param5: " + param5);
//			return "redirect:/ch03/content";
//		}
	
	@GetMapping("/method1")
	public String method1(Ch03Dto dto) {
		logger.info("실행");
		logger.info("param1: " + dto.getParam1());
		logger.info("param2: " + dto.getParam2());
		logger.info("param3: " + dto.getParam3());
		logger.info("param4: " + dto.isParam4());
		logger.info("param5: " + dto.getParam5());
		return "redirect:/ch03/content";
	}
	
//	@PostMapping("/method2")
//	public String method2(
//			String param1,
//			@RequestParam(defaultValue="0") int param2,
//			@RequestParam(defaultValue="0.0") double param3,
//			@RequestParam(defaultValue="false") boolean param4,
//			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5
//	) {
//		logger.info("실행");
//		logger.info("param1: " + param1);
//		logger.info("param2: " + param2);
//		logger.info("param3: " + param3);
//		logger.info("param4: " + param4);
//		logger.info("param5: " + param5);
//		return "redirect:/ch03/content";
//	}
	
	@PostMapping("/method2")
	public String method2(Ch03Dto dto) {
		logger.info("실행");
		logger.info("param1: " + dto.getParam1());
		logger.info("param2: " + dto.getParam2());
		logger.info("param3: " + dto.getParam3());
		logger.info("param4: " + dto.isParam4());
		logger.info("param5: " + dto.getParam5());
		return "redirect:/ch03/content";
	}
}
