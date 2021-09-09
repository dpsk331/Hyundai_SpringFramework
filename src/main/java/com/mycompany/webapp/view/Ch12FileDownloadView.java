package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.mycompany.webapp.controller.Ch12Controller;

@Component
public class Ch12FileDownloadView extends AbstractView {
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, //request 범위에 저장되어 있는 것을 가져올 수 있음!
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("실행");
		
		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");
		
		// fileNo를 이용해서 DB에서 파일 정보를 가져오기
		String contentType = request.getServletContext().getMimeType(fileName);
		String originalFilename = fileName;
		String savedName = fileName;
		
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
//		FileCopyUtils.copy(is, os); // 조금조금씩 읽고 출력하는 형식
		
		//자바 18장 복습
		byte[] data = new byte[1024];
		int readByteNum = -1;
		while(true) { 
			//읽기
			readByteNum = is.read(data);
			if(readByteNum == -1) break;
			//쓰기
			os.write(data, 0, readByteNum);
			os.flush();
		}
						
		is.close();
//		os.flush();
		os.close();
		
	}

}
