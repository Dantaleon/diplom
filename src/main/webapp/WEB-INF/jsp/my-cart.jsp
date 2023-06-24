<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.entity.CartsItems"%>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<link rel="stylesheet" type="text/css" href="css/cart.css">
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div id="divCart">
		<table>
			<thead>
				<tr>
					<th class="alignCenter">ID</th>
					<th class="alignCenter">Кол-во</th>
					<th class="alignCenter">[ID Cart] User email</th>
					<th class="alignCenter">[ID Window] Name</th>
					<th class="alightCenter">Удалить</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="alignCenter" colspan="4">Общая стоимость корзины:
						${cartsItemsList[0].cart.sum_cost}</td>
				</tr>
				<c:forEach var="cartItems" items="${cartsItemsList}">
					<tr>
						<td class="alignCenter">${cartItems.id}</td>
						<td class="alignRight">${cartItems.amount}</td>
						<td class="alignCenter">[${cartItems.cart.id}]
							${cartItems.cart.user.email}</td>
						<td class="alignCenter">[${cartItems.commonWindow.id}]
							${cartItems.commonWindow.name}</td>
						<td class="alignCenter"><button class="btnDelete"
								onclick="deleteWindowFromCart(${cartItems.commonWindow.id})">Удалить</button></td>
						<td class="alignCenter">
							<button type="button" class="buttonDetails"
								onclick="window.open('http://localhost:8080/Controller?ActionCommand=WindowDetails&wid=${cartItems.commonWindow.id}', '_blank')">
								Подробнее</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button onclick="showEditDiv()">Переместить в заказ</button>
	</div>
	<div id="editOrderReq" hidden="hidden">
	<table>
	<tr>
		<td>
			<label for="payvar">Вар.оплаты: </label>
		</td>
		<td>
			<select name="payvar">
				<!-- <option value="1">картой, онлайн</option> -->
				<option selected="selected" value="2">при получении</option>
			</select>
		</td>
	</tr>
	<tr><td colspan="2"><button id="submitTransfer" onclick="submitTransfer()">Подтвердить</button></td></tr>
	</table>
	</div>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
	<script>
	function deleteWindowFromCart(wid) {
	
	let cxhr = new XMLHttpRequest();
	
	cxhr = new XMLHttpRequest();
	cxhr.open('POST', '/ControllerAJAX?ActionCommand=CartUCPAJAX&sub=delWindowById&wid=' + wid , true);
	cxhr.setRequestHeader('Content-type', 'application/json');
	
	cxhr.onreadystatechange = function() {
	    if(cxhr.readyState == 4 && cxhr.status == 200) {
	      
			let strResp = this.responseText;
    		console.log(strResp);
	    }
	}
	cxhr.send();
}
	function showEditDiv() {
		let editDiv = document.getElementById('editOrderReq');
		editDiv.style.display = 'block';
	}
	function submitTransfer() {
		
		let pvar = document.getElementsByName('payvar')[0].value;
		let cxhr = new XMLHttpRequest();
		
		cxhr = new XMLHttpRequest();
		cxhr.open('POST', '/ControllerAJAX?ActionCommand=CartUCPAJAX&sub=cartToOrder&payVarId=' + pvar , true);
		cxhr.setRequestHeader('Content-type', 'application/json');
		
		cxhr.onreadystatechange = function() {
		    if(cxhr.readyState == 4 && cxhr.status == 200) {
		      
				let strResp = this.responseText;
	    		console.log(strResp);
		    }
		}
		cxhr.send();
	}
</script>
</body>
</html>