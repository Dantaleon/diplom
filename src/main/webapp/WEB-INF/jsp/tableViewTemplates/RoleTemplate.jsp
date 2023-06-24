<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.Role" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="role" items="${roleList}">
      <tr>
        <td class="alignCenter">${role.id}</td>
        <td class="alignLeft">${role.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
