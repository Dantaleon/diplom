<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.CommonWindow" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
      <th class="alignCenter">Натур.шир.</th>
      <th class="alignCenter">Натур.высота.</th>
      <th class="alignCenter">Натур.толщ.</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="commonWindow" items="${commonWindows}">
      <tr>
        <td class="alignCenter">${commonWindow.id}</td>
        <td class="alignLeft">${commonWindow.name}</td>
        <td class="alignCenter">${commonWindow.realw}</td>
        <td class="alignCenter">${commonWindow.realh}</td>
        <td class="alignCenter">${commonWindow.realth}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>