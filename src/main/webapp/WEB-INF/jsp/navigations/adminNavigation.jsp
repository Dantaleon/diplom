<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css" />

<div class="main-menu">
	<ul>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Home">Домой</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Catalog">Каталог</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Design">Дизайн</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=MyCart">Корзина</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=MyOrders">Заказы</a></li>
		<li><a href="http://localhost:8080/ControllerAJAX?ActionCommand=Logout">Выход</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=AdminTableView">Таблицы БД</a></li>
		<li><a href="http://localhost:8080/Controller?ActionCommand=Contact">Контакты</a></li>
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