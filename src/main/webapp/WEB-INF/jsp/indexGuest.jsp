<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/homeMain.css" />
<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
<div id="homeMain">
	
	<div id="designAddDiv">
	<a href="/Controller?ActionCommand=Login" >
		<img src="/images_native/designimg1.PNG" />
	</a>
	</div>
	
</div>
<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />