package com.mycompany.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 매개변수를 모두 받는 생성자
public class Ch11Skill {
	private int code;
	private String label;
	
//	public Ch11Skill() {}
	
	//생성자 오버로딩
//	public Ch11Skill(int code, String label) {
//		this.code = code;
//		this.label = label;
//	}

}
