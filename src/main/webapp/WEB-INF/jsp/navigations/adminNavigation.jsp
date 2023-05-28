<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css" />

<div class="main-menu">
	<ul>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Home">Home</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Catalog">Catalog</a></li>
		<li><a href="#">Design</a></li>
		<li><a href="#">Upload Image</a></li>
		<li><a href="http://localhost:8080/ControllerAJAX?ActionCommand=Logout">Logout</a></li>
		<li><a href="#">Contact</a></li>
		<li><a href="#">About</a></li>
		<li class="userLink">${nickname}[${role}](id:${id})</li>
	</ul>
</div>
<p id="p-error" class="error">${error}</p>
<p id="p-noError" class="p-noError">${noError}</p>

<script type="text/javascript">
	if (!document.getElementById("p-error").firstChild)
		document.getElementById("p-error").style.display = "none";
	if (!document.getElementById("p-noError").firstChild)
		document.getElementById("p-noError").style.display = "none";
</script>