<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.Cart" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID Cart</th>
      <th class="alignCenter">[ID User] UserEmail</th>
      <th class="alignCenter">Стоимость</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="cart" items="${cartList}">
      <tr>
        <td class="alignCenter">${cart.id}</td>
        <td class="alignCenter">[${cart.user.id}] ${cart.user.email}</td>
        <td class="alignRight">${cart.sum_cost}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>