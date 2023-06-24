<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.PaymentVar" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="paymentVar" items="${paymentVarList}">
      <tr>
        <td class="alignCenter">${paymentVar.id}</td>
        <td class="alignLeft">${paymentVar.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>