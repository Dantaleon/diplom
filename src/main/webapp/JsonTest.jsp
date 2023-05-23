<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="ajaxFunction1()">Click to recieve JSON via AJAX</button>
<script type="text/javascript">
	function ajaxFunction1(){
		let xhr = new XMLHttpRequest();
		
		xhr.open('GET','../JsonObjectImg', true);
		
		xhr.onload = function(){
			if (xhr.status == 200){
				console.log('success');
				let dog = JSON.parse(this.response);
				console.log(dog);
				
				const myDiv = document.createElement('div');
				myDiv.innerHTML = dog.phone;
				
				document.body.appendChild(myDiv);
			}
		}
		
		xhr.send();
		
	}
</script>
</body>
</html>