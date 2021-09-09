package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({"inputForm"})
public class Ch08Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces="application/json; charset=UTF-8")
	@ResponseBody // 응답 바디에 들어가는 데이터를 리턴하게 됨
	public String saveData(String name, HttpServletRequest request, HttpSession session) {
		logger.info("실행");
		logger.info("name: " + name);
		
//		HttpSession session = request.getSession(); // HttpServletRequest 사용시
		session.setAttribute("name", name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "seccess");
		String json = jsonObject.toString(); // {"result":"success"}
		return json; // 응답으로 보낸다
	}
	
	@GetMapping(value="/readData", produces="application/json; charset=UTF-8")
	@ResponseBody 
	public String readData(HttpSession session, @SessionAttribute String name) { //@SessionAttribute("name") String username) {
		logger.info("실행");
		
		//방법1
//		String name = (String)session.getAttribute("name");
//		logger.info("name: " + name);
		
		//방법2
		logger.info("name" + name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString(); // {"name":"홍길동
		return json; 
	}
	
	@GetMapping("/login")
	public String logiForm() {
		logger.info("실행");
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		if(mid.equals("spring") && mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");
		
		//방법1
		session.removeAttribute("sessionMid");
		
		//방법2 : session 안에 모든 데이터를 날리는 방법
//		session.invalidate();
		
		return "redirect:/ch08/content";
	}
	
	@PostMapping(value="/loginAjax", produces="apllication/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		String result = "";
		
		if(!mid.equals("spring")) {
			result ="wrongMid";
		} else if (!mpassword.equals("12345")) {
			result = "wrongMpassword";
		} else {
			result = "success";
			session.setAttribute("sessionMid", mid);
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();
		return json; 
	}
	
	@GetMapping("/logoutAjax")
	public void logoutAjax(HttpSession session, HttpServletResponse response) throws IOException {
		logger.info("실행");
		
		session.invalidate();
//		session.removeAttribute("sessionMid");
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "seccess");
		String json = jsonObject.toString();
		
		pw.println(json);
//		pw.flush();
//		pw.close();
	}
	
	//세션 input 이름이 존재하지 않을 경우 딱 한번 실행
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputForm() {
		logger.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
	
	
	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		return "ch08/inputStep1";
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		logger.info("실행");
		
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		//처리 내용
		//세션에 저장되어 있는 inpurForm을 제거
		sessionStatus.setComplete();
		
		return "redirect:/ch08/content";
	}
}

