<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navigations/header.jsp" />
	<form method="post" action="/login">
		<table>
		<th colspan="2"><h2>Логин</h2></th>
			<tr>
				<td>
					<label for="email">Эл. Почта:</label>
				</td>
				<td>
					<input type="email" id="email" name="email"></input>
				</td>
			</tr>
			<tr>
				<td>
					<label for="password">Пароль:</label>
				</td>
				<td>
					<input type="password" id="password" name="password"></input>
				</td>
			</tr>
			<tr colspan="2">
				<td>
					<input type="submit" value="Вход"></input>
				</td>
			</tr>
			<tr>
				<td><br>Нет аккаунта? <a href="/registration">Регистрация</a></td>
			</tr>
		</table>
	</form>
</body>
</html>