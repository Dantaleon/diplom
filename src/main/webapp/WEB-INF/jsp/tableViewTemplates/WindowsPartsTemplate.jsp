<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.WindowsParts" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">[ID Окна] name</th>
      <th class="alignCenter">ID Кусочка</th>
      <th class="alignCenter">img Кусочка</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="windowsParts" items="${windowsPartsList}">
      <tr>
        <td class="alignCenter">${windowsParts.id}</td>
        <td class="alignRight">[${windowsParts.commonWindow.id}] ${windowsParts.commonWindow.name}</td>
        <td class="alignCenter">${windowsParts.piece.id} </td>
        <td class="alignCenter pieceImage"><img src="data:image/png;base64,${windowsParts.piece.base64Img}"/></td>
      </tr>
    </c:forEach>
  </tbody>
</table>