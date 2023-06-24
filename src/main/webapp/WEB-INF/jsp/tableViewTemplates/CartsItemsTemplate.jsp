<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.CartsItems" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Кол-во</th>
      <th class="alignCenter">[ID Cart] User email</th>
      <th class="alignCenter">[ID Window] Name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="cartItems" items="${cartsItemsList}">
      <tr>
        <td class="alignCenter">${cartItems.id}</td>
        <td class="alignRight">${cartItems.amount}</td>
        <td class="alignCenter">[${cartItems.cart.id}] ${cartItems.cart.user.email}</td>
        <td class="alignCenter">[${cartItems.commonWindow.id}] ${cartItems.commonWindow.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>