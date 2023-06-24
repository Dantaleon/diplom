<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.OrderAcc" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignRight">Sum cost</th>
      <th class="alignCenter">Date оформ.</th>
      <th class="alignCenter">Date дост.</th>
      <th class="alignCenter">[ID] Оплата</th>
      <th class="alignCenter">[ID] User</th>
      <th class="alignCenter">[ID] Статус</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="orderAcc" items="${orderAccList}">
      <tr>
        <td class="alignCenter">${orderAcc.id}</td>
        <td class="alignRight">${orderAcc.sum_cost}</td>
        <td class="alignRight">${orderAcc.dateOform}</td>
        <td class="alignRight">${orderAcc.dateDeliver}</td>
        <td class="alignCenter">[${orderAcc.paymentVar.id}] ${orderAcc.paymentVar.name}</td>
        <td class="alignCenter">[${orderAcc.user.id}] ${orderAcc.user.email}</td>
        <td class="alignCenter">[${orderAcc.orderStatus.id}] ${orderAcc.orderStatus.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>