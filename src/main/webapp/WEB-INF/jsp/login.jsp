<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Вход</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<form id="loginForm" method="post" action="/Controller?ActionCommand=Login">
		<table>
		<th colspan="2"><h2>Логин</h2></th>
			<tr>
				<td class=alnRight>
					<label for="email">Эл. Почта:</label>
				</td>
				<td>
					<input type="email" id="email" name="email"></input>
				</td>
			</tr>
			<tr>
				<td class=alnRight>
					<label for="password">Пароль:</label>
				</td>
				<td>
					<input type="password" id="password" name="password"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2"  class="alnCenter">
					<input type="submit" value="Вход"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="alnCenter"><br>Нет аккаунта? <a href="/Controller?ActionCommand=Registration">Регистрация</a></td>
			</tr>
		</table>
	</form>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>