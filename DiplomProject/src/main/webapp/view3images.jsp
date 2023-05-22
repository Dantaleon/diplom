<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="navigations/header.jsp" />
	<div class="catalog" style="flex-direction: row; margin: 10">
	<c:forEach begin="0" end="12" step="1" var="id">
		<c:set var="stream" value="" />
		<img src="${pageContext.request.contextPath}/images/${id}.png" width="100"/>
	</c:forEach>
	</div>
</body>
</html>