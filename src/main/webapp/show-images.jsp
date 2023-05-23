<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if (request.getParameter("array") != null){
		%> array is not null<%
	} else {
		%> array is null<%
	}%>
</body>
</html>