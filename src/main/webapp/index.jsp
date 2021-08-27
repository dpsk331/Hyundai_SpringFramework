<%@ page contentType="text/html; charset=UTF-8"%>

<%
//System.out.println(request.getContextPath());
//System.out.println(request.getServletContext().getContextPath());
//System.out.println(application.getContextPath());

// 절대 걍로 사용시 경로를 동적으로 사용 가능
response.sendRedirect(application.getContextPath() + "/ch01/content");
%>