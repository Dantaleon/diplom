<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.MaterialType" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
      <th class="alignCenter">Множитель</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="materialType" items="${materialTypeList}">
      <tr>
        <td class="alignCenter">${materialType.id}</td>
        <td class="alignLeft">${materialType.name}</td>
        <td class="alignRight">${materialType.multiplier}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>