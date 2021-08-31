<%@ page contentType="text/html; charset=UTF-8"%>

<!-- "서버 내부의 경로" -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div class="card m-2">
	<div class="card-header">
		프로젝트 생성 및 실행
	</div>
	<div class="card-body">
		<!-- 브라우저가 요청하는 내용이기 때문에 위의 경로처럼 /WEB-INF 경로 사용 불가능 -->
		<img src="${pageContext.request.contextPath}/resources/images/logo-spring.png"
			 width="200px"/>
		<hr/>
		1. STS 설치    <br/>
		2. 플러그인 설치 <br/>
		3. 프로젝트 생성 <br/>
		4. 프로젝트 설정 <br/>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>