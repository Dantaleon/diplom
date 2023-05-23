<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/navigations/css/header.css" />

<%if (session.getAttribute("role") == "user"){ %>
	<jsp:include page="userNavigation.jsp"></jsp:include>
<%} else if (session.getAttribute("role") == "admin"){ %>
	<jsp:include page="adminNavigation.jsp"></jsp:include>
<%} else { %>
	<jsp:include page="guestNavigation.jsp"></jsp:include>
<%} %>
<p id="p-error" class="error">${error}</p>
<p id="p-noError" class="p-noError">${noError}</p>

<script type="text/javascript">
	if (!document.getElementById("p-error").firstChild)
		document.getElementById("p-error").style.display = "none";
	if (!document.getElementById("p-noError").firstChild)
		document.getElementById("p-noError").style.display = "none";
</script>