<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navigations/header.jsp" />
	
	<select id="tableSelect">
		<option value = "TableUser_acc"> User_acc </option>
		<option value = "TableRole"> Role </option>
	</select>
	
	<div id="tableDiv">
		
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
				    	script.src = '/js/edit' + table + '.js';
				    	script.onload = function() {
				    		console.log('editTableUser_acc.js is uploaded')
				    	};
				    	document.head.appendChild(script);
				    }
				};
				xhr.open('GET', '/ControllerAJAX?ActionCommand=' + table);
				xhr.send();
			});
	</script>
	
</body>
</html>