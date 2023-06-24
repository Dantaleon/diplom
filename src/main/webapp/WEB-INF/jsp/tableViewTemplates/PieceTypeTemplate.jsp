<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.PieceType" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="pieceType" items="${pieceTypeList}">
      <tr>
        <td class="alignCenter">${pieceType.id}</td>
        <td class="alignLeft">${pieceType.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>