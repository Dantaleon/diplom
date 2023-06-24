<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Регистрация</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/registration.css" />
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<form id="regForm" method="post" action="/Controller?ActionCommand=Registration">
		<table>
		<th colspan="2"><h2>Регистрация</h2></th>
			<tr>
				<td class="alnRight">
					<label for="nickname">Имя пользователя: </label>
				</td>
				<td>
					<input type="text" id="nickname" name="nickname"></input>
				</td>
			</tr>
			<tr>
				<td class="alnRight">
					<label for="email">Эл. Почта: </label>
				</td>
				<td>
					<input type="email" id="email" name="email"></input>
				</td>
			</tr>
			<tr>
				<td class="alnRight">
					<label for="password">Пароль: </label>
				</td>
				<td>
					<input type="password" id="password" name="password"></input>
				</td>
			</tr>
			<tr>
				<td class="alnRight">
					<label for="confirmPassword">Повторите пароль: </label>
				</td>
				<td>
					<input type="password" id="confirmPassword" name="confirmPassword"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="alnCenter">
					<input type="submit" value="Подтвердить"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="alnCenter"><br>Уже есть аккаунт? <a href="/Controller?ActionCommand=Login">Вход</a></td>
			</tr>
		</table>
	</form>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>