<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.entity.OrdersItems"%>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<link rel="stylesheet" type="text/css" href="css/order.css">
</head>
<body>
<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div id="divOrder">
	<table>
	  <thead>
	    <tr>
	      <th class="alignCenter">ID</th>
	      <th class="alignRight">Sum cost</th>
	      <th class="alignCenter">Date оформ.</th>
	      <th class="alignCenter">Date дост.</th>
	      <th class="alignCenter">Оплата</th>
	      <th class="alignCenter">[ID] User</th>
	      <th class="alignCenter">Статус</th>
	      <th class="alignCenter">Подробнее</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var="orderAcc" items="${orderAccList}">
	      <tr>
	        <td class="alignCenter">${orderAcc.id}</td>
	        <td class="alignRight">${orderAcc.sum_cost}</td>
	        <td class="alignRight">${orderAcc.dateOform}</td>
	        <td class="alignRight">${orderAcc.dateDeliver}</td>
	        <td class="alignCenter">${orderAcc.paymentVar.name}</td>
	        <td class="alignCenter">[${orderAcc.user.id}] ${orderAcc.user.email}</td>
	        <td class="alignLeft">${orderAcc.orderStatus.name}</td>
	        <td>
	        <button type="button" class="buttonDetails"
					onclick="window.open('http://localhost:8080/Controller?ActionCommand=OrderDetails&oid=${orderAcc.id}', '_blank')">
					Подробнее
			</button>
	        </td>
	      </tr>
	    </c:forEach>
	  </tbody>
	</table>
	</div>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>