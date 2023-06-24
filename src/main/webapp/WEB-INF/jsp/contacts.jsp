<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/contact.css" />
</head>
<body>
<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
<div id="contactDiv">
<div id="contactInfo">
	<p>Адрес:<br/>ул. Оконная, д.20, кв. 5, Красногорск, Московская область</p>
	<p>Телефон:<br/>8-887-654-45-45</p>
</div>
<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</div>
</body>
</html>