<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.UserDetails" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">[ID details] email</th>
      <th class="alignCenter">Город</th>
      <th class="alignCenter">Адрес</th>
      <th class="alignCenter">Телефон</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="userDetails" items="${userDetailsList}">
      <tr>
        <td class="alignCenter">[${userDetails.user.id}] ${userDetails.user.email}</td>
        <td class="alignLeft">${userDetails.city}</td>
        <td class="alignLeft">${userDetails.address}</td>
        <td class="alignLeft">${userDetails.phone}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>