<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		예외 처리
	</div>
	<div class="card-body">
	
		<div class="card">
			<div class="card-header">
				try-catch를 이용한 예외 처리
			</div>
			<div class="card-body">
				<a href="handlingException1" class="btn btn-danger btn-sm">예외 처리 - NullPointerException</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				예외 처리 클래스 이용한 예외 처리 - NullPointerException
			</div>
			<div class="card-body">
				<a href="handlingException2" class="btn btn-danger btn-sm">예외 처리 - NullPointerException</a>
				<a href="handlingException3" class="btn btn-danger btn-sm">예외 처리 - ClassCastException</a>
				<a href="handlingException4" class="btn btn-danger btn-sm">예외 처리 - ArrayIndexOutOfBoundsException</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				사용자 정의 예외 처리
			</div>
			<div class="card-body">
				<a href="handlingException5" class="btn btn-danger btn-sm">예외 처리</a>
			</div>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>