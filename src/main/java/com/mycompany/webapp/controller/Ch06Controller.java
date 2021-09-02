package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
//	public Ch06Controller() {
//		logger.info("실행"); // 컨트롤러 객체 자동 생성 확인해보기
//	}
	
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@RequestMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@RequestMapping("/redirect")
	public String redirect() {
		return "redirect:/"; // 홈으로 이동
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmetnHtml() {
		logger.info("실행");
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo5.jpg");
		String json = jsonObject.toString();
		
		// ▼ 응답 HTTP의 body 부분에 json을 포함시켜서 보내는 코드
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter pw = response.getWriter(); // 직접 출력 스트림 이용 방법
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	// 위의 getJson1()과 동일한 동작을 하는 코드
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8")
	@ResponseBody // 위의 출력 스트림 부분을 해당 어노테이션에서 처리해줌
	public String getJson2() {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		return json;
	}
	
	// 아래와 같이 ajax는 redirect를 사용하지 말 것 xxx !! ! 
	@GetMapping("/getJson3") // json을 받을 생각으로 요청한 것인데 리다이렉트를 하면 홈으로 가버림.. 말이 안됨...
	public String getJson3() {
		logger.info("실행");
		return "redirect:/";
	}
}
