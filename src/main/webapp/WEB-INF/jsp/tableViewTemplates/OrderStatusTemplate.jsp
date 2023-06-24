<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.OrderStatus" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="orderStatus" items="${orderStatusList}">
      <tr>
        <td class="alignCenter">${orderStatus.id}</td>
        <td class="alignLeft">${orderStatus.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>