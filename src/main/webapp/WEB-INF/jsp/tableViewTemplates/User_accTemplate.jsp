<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.User" %>

<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Nickname</th>
      <th class="alignCenter">Email</th>
      <th class="alignCenter">Пароль</th>
      <th class="alignCenter">Удаление</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td class="alignCenter">${user.id}</td>
        <td class="alignLeft">${user.nickname}</td>
        <td class="alignLeft">${user.email}</td>
        <td class="alignLeft">${user.password}</td>
        <td class="alignCenter"><button class="deleteBtn"onclick="deleteRecord(${user.id})">Удалить</button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<div>
	<button class="createBtn" onclick="createRecord()">Создать</button>
</div>

<div id="editRecord" hidden="hidden">
	<table>
	<tr>
		<td>
			<label for="id">ID: </label>
		</td>
		<td>
			<input id="inpId" type=number name="id" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="nickname">Nickname: </label>
		</td>
		<td>
			<input id="inpNickname" type=text name="nickname" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="email">Email: </label>
		</td>
		<td>
			<input id="inpEmail" type=email name="email" required="required"></input>
		</td>
	<tr>
		<td>
			<label for="password">Пароль: </label>
		</td>
		<td>
			<input id="inpPassword" type=text name="password" required="required"></input>
		</td>
	</tr>
	<tr>
	<td>
		<button class="createBtn" id="btnCreate" onclick="createRecordSubmit()">Создать</button>
		<button class="editBtn" id="btnEdit" onclick="editRecordSubmit()">Редактировать</button>
	</td>
	</tr>
	</table>
</div>