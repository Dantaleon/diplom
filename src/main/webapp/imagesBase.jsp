<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Display</title>
</head>
<body>
    <%-- Call the servlet to retrieve the image --%>
    <jsp:include page="/ImagesBaseServlet?id=1" />

    <img src="<c:url value="/ImagesBaseServlet?id=1" />" alt="image">
</body>
</html>
