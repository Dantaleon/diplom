<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload image test</title>
</head>
<body>
<jsp:include page="navigations/header.jsp" />
	<p>${isUpload}</p>
	<div id="div-upload-image">
		<form action="uploadImage" enctype="multipart/form-data" method="post">
			<input type="file" name="input-images" />
			<input type="submit" value="Подтвердить"/>
		</form>
	</div>
</body>
</html>