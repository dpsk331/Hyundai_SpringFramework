<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드값을 양식의 드롭다운리스트(checkbox 태그)로 세팅
	</div>
	<div class="card-body">

		<form method="get" action="form4">
			<div>
				<c:forEach var="job" items="${jobList}" varStatus="status">
					<span>
						<input type="radio" id="lang${status.count}" name="mjob" value="${job}"
							<c:if test="${member.mjob == job}">checked</c:if>
						/>
						<label for="lang${status.count}">${job}</label>
					</span>
				</c:forEach>
			</div>
			<button class="btn btn-info btn-sm">제출</button>
		</form>	
		
		<form:form modelAttribute="member" method="post" action="form4" class="mt-3">
 			<div>
 				<form:radiobuttons items="${jobList}" path="mjob"/>
 			</div>
 			<button class="btn btn-info btn-sm">제출</button>
 		</form:form>
 
 		<form:form modelAttribute="member" method="post" action="form4" class="mt-3">
 			<div>
 				<form:radiobuttons items="${cityList}" path="mcity" itemValue="code" itemLabel="label"/>
 			</div>
 			<button class="btn btn-info btn-sm">제출</button>
 		</form:form>
 		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>