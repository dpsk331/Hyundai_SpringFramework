package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileUpload(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "_" + attach.getOriginalFilename();
		File file = new File("D:/WorkSpace/Hyundai/Spring_upload_files/" + savedname);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileUploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAjax(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "_" + attach.getOriginalFilename();
		File file = new File("D:/WorkSpace/Hyundai/Spring_upload_files/" + savedname);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();
		
		return json;
	}
	
	// 좋은 방식이 아님
	// 단점1) 응답 바디의 데이터 형식이 고정
	// 단점2) toByteArray()에 리턴하는 배열의 길이 문제
//	@GetMapping(value="/filedownload", produces="image/jpeg")
//	@ResponseBody
//	public byte[] filedownload(String savedname) throws Exception {
//		String filePath = "D:/WorkSpace/Hyundai/Spring_upload_files/" + savedname;
//		InputStream is = new FileInputStream(filePath);
//		byte[] data = IOUtils.toByteArray(is);
//		return data;
//	}
	
	// 메모리를 적게 차지하는 더 좋은 방법
	@GetMapping("/filedownload")
	public void filedownload(
			int fileNo, 
			HttpServletResponse response,
			@RequestHeader("User-Agent") String userAgent) throws Exception {
		// fileNo를 이용해서 DB에서 파일 정보를 가져오기
		String contentType = "image/jpeg";
		String originalFilename = "photo12.jpg";
		String savedName = "1630866391732_photo12.jpg";
		
		// 응답 바디의 데이터의 형식
		response.setContentType(contentType);

		// 브라우저 별로 한글 파일명 변환
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) { 
			// IE
			originalFilename = URLEncoder.encode(originalFilename,"UTF-8");
		} else {
			// 크롬, 엣지, 사파리
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		// 파일을 첨부로 다운로드하도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		
		// 파일로부터 데이터를 읽는 입력스트림 생성
		String filePath = "D:/WorkSpace/Hyundai/Spring_upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);

		// 응답 바디에 출력하는 출력스트림 얻기
		OutputStream os = response.getOutputStream();
		
		//입력스트림 -> 출력스트립
		FileCopyUtils.copy(is, os); // 조금조금씩 읽고 출력하는 형식
		is.close();
		os.flush();
		os.close();
		
	}
}













