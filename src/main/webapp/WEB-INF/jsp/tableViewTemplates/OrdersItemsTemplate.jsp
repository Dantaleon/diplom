<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.OrdersItems" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Кол-во</th>
      <th class="alignCenter">[ID Заказа] Почта</th>
      <th class="alignCenter">[ID] Окно</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="ordersItems" items="${ordersItemsList}">
      <tr>
        <td class="alignCenter">${ordersItems.id}</td>
        <td class="alignRight">${ordersItems.amount}</td>
        <td class="alignCenter">[${ordersItems.orderAcc.id}] ${ordersItems.orderAcc.user.email}</td>
        <td class="alignCenter">[${ordersItems.commonWindow.id}] ${ordersItems.commonWindow.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>