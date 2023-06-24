<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.entity.OrdersItems"%>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<link rel="stylesheet" type="text/css" href="css/orderDetails.css">
</head>
<body>
<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div id="divOrderDetails">
		<table>
			<thead>
				<tr>
					<th class="alignCenter">ID</th>
					<th class="alignCenter">Кол-во</th>
					<th class="alignCenter">[ID Order] User email</th>
					<th class="alignCenter">[ID Window] Name<br />Подробнее
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderItem" items="${ordersItemsList}">
					<tr>
						<td class="alignCenter">${orderItem.id}</td>
						<td class="alignRight">${orderItem.amount}</td>
						<td class="alignCenter">[${orderItem.orderAcc.id}]
							${orderItem.orderAcc.user.email}</td>
						<td class="alignCenter">[${orderItem.commonWindow.id}]
							${orderItem.commonWindow.name} <br />
							<button type="button" class="buttonDetails"
								onclick="window.open('http://localhost:8080/Controller?ActionCommand=WindowDetails&wid=${orderItem.commonWindow.id}', '_blank')">
								Подробнее</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>