<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<p><h3>Registration</h3></p>
	<p>${regError}</p>
	
	<form method="post" action="/register">
		<label for="firstName">firstName</label>
		<input type="text" id="firstName" name="firstName"></input><br>
		
		<label for="lastName">lastName</label>
		<input type="text" id="lastName" name="lastName"></input><br>
		
		<label for="age">age</label>
		<input type="number" id="age" name="age"></input><br>
		
		<label for="nickname">nickname</label>
		<input type="text" id="nickname" name="nickname"></input><br>
		
		<label for="email">email</label>
		<input type="email" id="email" name="email"></input><br>
		
		<label for="telephone">telephone</label>
		<input type="tel" id="telephone" name="telephone"></input><br>
		
		<label for="password">password</label>
		<input type="password" id="password" name="password"></input><br>
		
		<label for="confirmPassword">confirmPassword</label>
		<input type="password" id="confirmPassword" name="confirmPassword"></input><br>
		
		<input type="submit" value="Registration"></input><br>
	</form>
</body>
</html>