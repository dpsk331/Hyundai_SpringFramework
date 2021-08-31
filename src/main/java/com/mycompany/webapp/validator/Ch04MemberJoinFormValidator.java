package com.mycompany.webapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

public class Ch04MemberJoinFormValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(Ch04MemberJoinFormValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("실행");
		boolean check = Ch04Member.class.isAssignableFrom(clazz); // clazz는 Ch04Member 클래스인가 유효성 검사(클래스 자신이거나 자식 클래스이면 유효성 검사 통과)
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("실행");
		Ch04Member member = (Ch04Member) target;
		
		// mid
		if(member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", "errors.mid.required");
		} else { // 무언가 입력이 되었다면
			if(member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength", new Object[] {4}, "");
			}
		}
		
		// mpassword 검사
		if(member.getMpassword() == null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required");
		} else {
			if(member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength", new Object[] {8}, "");
			}
		}
		
		// memail 검사
		if(member.getMemail() == null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail", "errors.memail.required");
		} else {
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher metcher = pattern.matcher(member.getMemail());
			if(!metcher.matches()) {
				errors.rejectValue("memail", "errors.memail.invalid");
			}
		}
		
		// mtel 검사
		if(member.getMtel() == null || member.getMtel().trim().equals("")) {
			errors.rejectValue("mtel", "errors.mtel.required");
		} else {
			String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher metcher = pattern.matcher(member.getMtel());
			if(!metcher.matches()) {
				errors.rejectValue("mtel", "errors.mtel.invalid");
			}
		}
		
	}

}
