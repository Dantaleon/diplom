<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.entity.WindowsParts"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/windowDetails.css" />
<title>Window Details</title>
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div class="wdetails-div">
		<table id="tableDetails">
			<thead>
				<tr>
					<th class="alignCenter">[ID Кусочка]</th>
					<th class="alignCenter">[Ширина, Высота, Толщина]</th>
					<th class="alignCenter">[тип]<br/>img Кусочка<br /></th>
					<th class="alignCenter"> Стоимость </th>
					<th class="alignLeft"> Тип материала </th>
				</tr>
			</thead>
			<tbody>
				<c:set var="windowCost" value="0"></c:set>
				<c:forEach var="windowsParts" items="${windowsPartsList}">
					<c:set var="windowCost" value="${windowCost + windowsParts.piece.cost}"></c:set>
				</c:forEach>
				<tr>
					<td class="alignCenter" colspan="5"><h3>Окно
						${windowsPartsList[0].commonWindow.id}</h3><br/>
						Общая стоимость: <fmt:formatNumber value="${windowCost}" maxFractionDigits="2" />&nbsp;руб.
					</td>
				</tr>
				<c:forEach var="windowsParts" items="${windowsPartsList}">
					<tr>
						<td class="alignCenter">[${windowsParts.piece.id}]</td>
						<td class="alignCenter">[${windowsParts.piece.realw},
							${windowsParts.piece.realh}, ${windowsParts.piece.realw}]</td>
						<td class="alignCenter">
							[${windowsParts.piece.pieceType.name}]<br/>
							<img class="productImg"
							src="data:image/png;base64,${windowsParts.piece.base64Img}" /><br />
						</td>
						<td class="alignRight"> ${windowsParts.piece.cost} руб.	</td>
						<td class="alignCenter"> ${windowsParts.piece.materialType.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>