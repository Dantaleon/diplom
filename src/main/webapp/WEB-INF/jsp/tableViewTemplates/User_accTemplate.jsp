<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.User" %>

<table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Nickname</th>
      <th>Email</th>
      <th>Пароль</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td>${user.id}</td>
        <td>${user.nickname}</td>
        <td>${user.email}</td>
        <td>${user.password}</td>
        <td><button onclick="editRecord(${user.id})">Редактировать</button></td>
        <td><button onclick="deleteRecord(${user.id})">Удалить</button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<div>
	<button onclick="createRecord()">Создать</button>
</div>

<div id="editRecord" hidden="hidden">
	<table>
	<tr>
		<td>
			<label for="id">ID: </label>
		</td>
		<td>
			<input type=number name="nickname"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="nickname">Nickname: </label>
		</td>
		<td>
			<input type=text name="nickname"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="email">Email: </label>
		</td>
		<td>
			<input type=email name="email"></input>
		</td>
	<tr>
		<td>
			<label for="password">Пароль: </label>
		</td>
		<td>
			<input type=text name="password"></input>
		</td>
	</tr>
	<tr>
	<td>
		<button id="btnCreate">Создать</button>
		<button id="btnEdit">Редактировать</button>
	</td>
	<td>
		<button id="btnClear">Очистить</button>
	</td>
	</tr>
	</table>
</div>