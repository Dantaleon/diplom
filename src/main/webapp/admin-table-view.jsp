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
	<!-- <script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		document.getElementById('tableSelect').addEventListener('change', function(){
			console.log(this.value);
		
			$.ajax({url: "/ControllerAJAX?ActionCommand=" + this.value,
					success: function(data){$("#tableDiv").html(data);} });
		});
	</script>-->
	<script type="text/javascript">
		document.getElementById('tableSelect').addEventListener('change', function()
			{
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() 
				{
					if (xhr.readyState === 4) {
				      	if (xhr.status === 200) {
				        	document.getElementById('tableDiv').innerHTML = xhr.responseText;
				      	} else {
				        	console.error('Request failed.  Returned status of ' + xhr.status);
				      	}
				    }
				};
				xhr.open('GET', '/ControllerAJAX?ActionCommand=' + this.value);
				xhr.send();
			});
	</script>
</body>
</html>