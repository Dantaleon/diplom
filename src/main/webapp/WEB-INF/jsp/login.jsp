<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<form method="post" action="/Controller?ActionCommand=Login">
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
				<td><br>Нет аккаунта? <a href="/Controller?ActionCommand=Registration">Регистрация</a></td>
			</tr>
		</table>
	</form>
</body>
</html>