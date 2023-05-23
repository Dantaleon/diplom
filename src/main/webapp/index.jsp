<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<jsp:include page="navigations/header.jsp" />
	Добро пожаловать,<br />
	Ваш id: <c:out value="${sessionScope.id}" /><br />
	Ваша роль: <c:out value="${sessionScope.role}" /> <br />
</body>
</html>