<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title> Canvas image</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
		<canvas id="canvas1" width="1200" height="800"></canvas>
		<!--<button onclick = "addChild()">Добавить картинку</button>-->
	</div>
	<div id="myImages">

	</div>
	<div id="divButtons">
		X<input type="range" min="-100" max="1500" value="7" step="1"  oninput="showVal(this.value, 'x')"><br /><input type="text" id="labelx"><br />
		Y<input type="range" min="-100" max="1121" value="7" step="1"  onchange="showVal(this.value, 'y')"><br /><input type="text" id="labely"><br />
		<hr>
		<button id="buttonL1" onclick="reloadDivByLayer(1)">
			Base
		</button><br/>
		<button id="buttonL2" onclick="reloadDivByLayer(2)">
			Up
		</button><br/>
		<button id="buttonL3" onclick="reloadDivByLayer(2)">
			Bottom
		</button><br/>
		<button id="buttonL4" onclick="reloadDivByLayer(3)">
			Left/Right
		</button><br/>
		<hr>
		<button id="buttonApply">
			Save
		</button><br/>
	</div>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>