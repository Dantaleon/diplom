<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.entity.WindowsParts"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalog</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/catalog.css" />
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div class="main-wrap">
		<div class="catalog-wrap">
			<div class="catalog-div">
				<table id="tableCatalog">
					<thead>
						<tr>
							<th class="alignCenter">[ID Окна] name</th>
							<th class="alignCenter">[ID Кусочка]<br/>img Кусочка</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="distinctWindows" items="${distinctWindowsList}">
							<c:set var="currentWindowId" value="${distinctWindows.commonWindow.id}"></c:set>
							
							<c:set var="windowCost" value="0"></c:set>
							<c:forEach var="windowsParts" items="${windowsPartsList}">
								<c:if test="${windowsParts.commonWindow.id == currentWindowId}">
									<c:set var="windowCost" value="${windowCost + windowsParts.piece.cost}"></c:set>
								</c:if>
							</c:forEach>

							<tr>
								<td class="alignCenter">[${currentWindowId}] ${distinctWindows.commonWindow.name}<br/>
								<p>Общая стоимость[руб]:<br/>${windowCost}</p><br/>
								<button type="button" class="buttonDetails" 
									onclick="window.open('http://localhost:8080/Controller?ActionCommand=WindowDetails&wid=${currentWindowId}', '_blank')">
								  Подробнее
								</button>
								<button class="buttonBuy" onclick="window.location.assign('/Controller?ActionCommand=Login')">
									Добавить в корзину
								</button>			
								</td>
								<c:forEach var="windowsParts" items="${windowsPartsList}">
									<c:if test="${windowsParts.commonWindow.id == currentWindowId}">
										<c:set var="windowCost" value="${windowCost + windowsParts.piece.cost}"></c:set>
										<td class="alignCenter pieceImage">
											[${windowsParts.piece.id}]
											<div class="divImgCatalog">
												<img class="productImg"
													src="data:image/png;base64,${windowsParts.piece.base64Img}" 
												/><br/>
												Стоимость[руб]: "${windowsParts.piece.cost}"
											</div>
										</td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
	<script type="text/javascript">
		function buyWindow(wid) {
			
			let cxhr = new XMLHttpRequest();
			
			cxhr = new XMLHttpRequest();
			cxhr.open('POST', '/ControllerAJAX?ActionCommand=CartUCPAJAX&sub=addWindowById&wid=' + wid , true);
			cxhr.setRequestHeader('Content-type', 'application/json');
			
			/* cxhr.onreadystatechange = function() {
			    if(cxhr.readyState == 4 && cxhr.status == 200) {
			      
					let strResp = this.responseText;
		    		console.log(strResp);
			    }
			} */
			cxhr.send();
		}
	</script>
</body>
</html>