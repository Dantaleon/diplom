<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Регистрация</title>
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<form method="post" action="/Controller?ActionCommand=Registration">
		<table>
		<th colspan="2"><h2>Регистрация</h2></th>
			<tr>
				<td>
					<label for="nickname">Имя пользователя: </label>
				</td>
				<td>
					<input type="text" id="nickname" name="nickname"></input>
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">Эл. Почта: </label>
				</td>
				<td>
					<input type="email" id="email" name="email"></input>
				</td>
			</tr>
			<tr>
				<td>
					<label for="password">Пароль: </label>
				</td>
				<td>
					<input type="password" id="password" name="password"></input>
				</td>
			</tr>
			<tr>
				<td>
					<label for="confirmPassword">Повторите пароль: </label>
				</td>
				<td>
					<input type="password" id="confirmPassword" name="confirmPassword"></input>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Подтвердить"></input>
				</td>
			</tr>
			<tr>
				<td><br>Уже есть аккаунт? <a href="/Controller?ActionCommand=Login">Вход</a></td>
			</tr>
		</table>
	</form>
</body>
</html>