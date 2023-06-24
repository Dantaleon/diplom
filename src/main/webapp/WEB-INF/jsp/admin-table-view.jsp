<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Table View</title>
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
	<div id=top-div style="width:100%">
		<table id="tableAction" style="padding: 5px">
		<tr>
			<th class="alignCenter"><h3>Таблица</h3></th>
			<th class="alignCenter"><h3>Действие</h3></th>
		</tr>
		<tr>
			<td>
				<select id="tableSelect">
					<option value = "TableUser_acc" selected> User_acc </option>
					<option value = "TableRole"> Role </option>
					<option value = "TableCommonWindow"> Common_Window </option>
					<option value = "TableCart"> Cart </option>
					<option value = "TableCartsItems"> CartsItems </option>
					<option value = "TableMaterialType"> MaterialType </option>
					<option value = "TablePieceType"> PieceType </option>
					<option value = "TablePiece"> Piece </option>
					<option value = "TablePaymentVar"> PaymentVar </option>
					<option value = "TableOrderStatus"> OrderStatus </option>
					<option value = "TableOrderAcc"> OrderAcc </option>
					<option value = "TableOrdersItems"> OrdersItems </option>
					<option value = "TableUserDetails"> UserDetails </option>
					<option value = "TableUsersRoles"> UsersRoles </option>
					<option value = "TableWindowsParts"> WindowsParts </option>
				</select>
			</td>
		<td>
			<button onclick="refreshTable()">Обновить</button>
		</td>
		</tr>
		</table>
	</div>
	<div id="tableDiv" align="center">
		
	</div>
	
	<script type="text/javascript">
		document.getElementById('tableSelect').addEventListener('change', function()
			{
				var table = this.value;
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() 
				{
					if (xhr.readyState === 4) {
				      	if (xhr.status === 200) {
				        	document.getElementById('tableDiv').innerHTML = xhr.responseText;
				      	} else {
				        	console.error('Request failed.  Returned status of ' + xhr.status);
				      	}
				      	
				      	var script = document.createElement('script');
				    	script.src = '/js/edit' + table + '.js?2';
				    	script.onload = function() {
				    		console.log('edit' + table +'.js uploaded')
				    	};
				    	document.head.appendChild(script);
				    }
				};
				xhr.open('GET', '/ControllerAJAX?ActionCommand=' + table);
				document.getElementById('tableDiv').innerHTML =
					'<p style="color: white; background-color: black;">Загрузка ' + table + ' ...</p>';
				//setTimeout(function(){xhr.send()}, 3000);
				xhr.send();
				
			});
		
		function refreshTable() {
			const selectElement = document.getElementById('tableSelect')
			const selectedOption = selectElement.options[selectElement.selectedIndex].value;
			console.log(selectedOption);
			selectElement.dispatchEvent(new Event('change'));
		}
		const selectElement = document.querySelector('select');
		selectElement.selectedIndex = 0;
		selectElement.dispatchEvent(new Event('change'));
		
	</script>
	<jsp:include page="/ControllerAJAX?ActionCommand=FooterInclude" />
</body>
</html>