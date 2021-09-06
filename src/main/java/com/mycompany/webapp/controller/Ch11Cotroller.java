package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch11City;
import com.mycompany.webapp.dto.Ch11Member;
import com.mycompany.webapp.dto.Ch11Skill;

@Controller
@RequestMapping("/ch11")
public class Ch11Cotroller {
	private static final Logger logger = LoggerFactory.getLogger(Ch11Cotroller.class);
	
	@RequestMapping("/content")
	public String content(String data) {
		logger.info("실행");
		return "ch11/content";
	}
	
	// form1을 요청하는 메서드
	@GetMapping("/form1")
	// 매개변수로 DTO가 오게 되면 request 범위에서 클래스명을 첫자를 소문자한 값으로 자동으로 관리됨
	// 기본으로 저장되는 이름을 변경하고 싶을 때는 @ModelAttribute 어노테이션 사용
	public String form1(@ModelAttribute("member") Ch11Member member) { 
		logger.info("실행");
		
		member.setMnation("한국");
				
		return "ch11/form1";
	}
	
	// form을 통해 입력 받은 데이터를 가져오는 메서드
	@PostMapping("/form1")
	public String handleForm1(@ModelAttribute("member") Ch11Member member) {
		logger.info("실행");
		
		logger.info("mid: " + member.getMid());
		logger.info("mname: " + member.getMname());
		logger.info("mpassword: " + member.getMpassword());
		logger.info("mnation: " + member.getMnation());
		
		return "redirect:/ch11/content";
	}
	
	@GetMapping("/form2")
	public String form2(@ModelAttribute("member") Ch11Member member, Model model) { 
		logger.info("실행");
		
		// DB에서 가져온 값이라고 가정, 옵션 태그 제공 목적
		// 드롭다운리스트의 항목을 추가할 목적
		List<String> typeList = new ArrayList<>();
		typeList.add("일반회원");
		typeList.add("기업회원");
		typeList.add("헤드헌터회원");
		model.addAttribute("typeList", typeList);
		
		member.setMtype("일반회원"); //default값 설정
		
		List<String> jobList = new ArrayList<>();
		jobList.add("학생");
		jobList.add("개발자");
		jobList.add("디자이너");
		model.addAttribute("jobList", jobList);
		
		member.setMjob("개발자");
		
		List<Ch11City> cityList = new ArrayList<>();
		cityList.add(new Ch11City(1, "서울"));
		cityList.add(new Ch11City(2, "부산"));
		cityList.add(new Ch11City(3, "제주"));
		model.addAttribute("cityList", cityList);
		
		member.setMcity(3);
		
		return "ch11/form2";
	}
	
	@GetMapping("/form3")
	public String form3(@ModelAttribute("member") Ch11Member member, Model model) { 
		logger.info("실행");
		
		List<String> languageList = new ArrayList<>();
		languageList.add("C");
		languageList.add("Python");
		languageList.add("Java");
		languageList.add("JavaScript");
		model.addAttribute("languageList", languageList);
		
		member.setMlanguage(new String[] {"Python", "Java"});
		
		List<Ch11Skill> skillList = new ArrayList<>();
		skillList.add(new Ch11Skill(1, "SpringFramework"));
		skillList.add(new Ch11Skill(2, "SpringBoot"));
		skillList.add(new Ch11Skill(3, "Vue"));
		model.addAttribute("skillList", skillList);
		
		member.setMskill(new String[] {"SpringFramework", "Vue"});
		
		return "ch11/form3";
	}
}
















