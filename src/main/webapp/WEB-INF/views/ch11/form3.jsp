<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드값을 양식의 드롭다운리스트(checkbox 태그)로 세팅
	</div>
	<div class="card-body">
<%-- 	
		<form>
			<c:forEach var="language" items="${languageList}" varStatus="status">
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" 
				  		 id="lang${status.count}" name="mlanguage" value="${language}"
				  		 <c:forEach var="temp" items="${member.mlanguage}">
				  		 	<c:if test="${temp == language}">checked</c:if>
				  		 </c:forEach>
				  		 >
				  <label class="form-check-label" for="lang${status.count}">${language}</label>
				</div>
			</c:forEach>
		</form>	
 --%>
 
 		<form:form modelAttribute="member">
 			<div class="form-check form-check-inline">
 				<form:checkboxes items="${languageList}" path="mlanguage" class="ml-2 mr-1"/>
 			</div>
 		</form:form>
 		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>