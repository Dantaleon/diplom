<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.UsersRoles" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">[ID User] email</th>
      <th class="alignCenter">[ID Role] name</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="usersRoles" items="${usersRolesList}">
      <tr>
        <td class="alignCenter">[${usersRoles.user.id}] ${usersRoles.user.email}</td>
        <td class="alignRight">[${usersRoles.role.id}] ${usersRoles.role.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>