<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		Controller/Request Parameter
	</div>
	<div class="card-body">
		<div class="card m-2">
			<div class="card-header">
				GET 방식으로 요청
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" 
				   href="method1?param1=문자열&param2=5&param3=3.14&param4=true&param5=2021-08-27">요청</a> <!-- 쿼리스트링 : 무언가를 요청할 때 보내는 문자열 -->
				<hr/>
				<form method="get" action="method1">
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
	                   <input type="text" name="param1" class="form-control" value="문자열">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
	                   <input type="text" name="param2" class="form-control" value="5">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
	                   <input type="text" name="param3" class="form-control" value="3.14">
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
	                   <div class="btn-group btn-group-toggle" data-toggle="buttons">
	                      <label class="btn btn-secondary active">
	                        <input type="radio" name="param4" checked value="true"> true
	                      </label>
	                      <label class="btn btn-secondary">
	                        <input type="radio" name="param4" value="false"> false
	                      </label>
	                   </div>
	                </div>
	                <div class="input-group">
	                   <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
	                   <input type="date" name="param5" class="form-control" value="2030-12-05">
	                </div>
	                <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
	             </form>
			</div>
		</div>		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>