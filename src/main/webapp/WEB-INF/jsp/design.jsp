<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title> Design </title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<jsp:include page="/ControllerAJAX?ActionCommand=HeaderInclude" />
		<canvas id="canvas1" width="1200" height="800"></canvas>
		<!--<button onclick = "addChild()">Добавить картинку</button>-->
	<div id="myImages">

	</div>
	<div id="divButtons">
		X<input id="rangex" type="range" min="-100" max="1500" value="7" step="1"  oninput="showVal(this.value, 'x')" ><br /><input type="text" id="labelx" oninput="rangex.value = labelx.value"><br />
		Y<input id="rangey" type="range" min="-100" max="1121" value="7" step="1"  oninput="showVal(this.value, 'y')" ><br /><input type="text" id="labely" oninput="rangey.value = labely.value"><br />
		W<input id="rangew" type="range" min="-100" max="1500" value="100" step="1"  oninput="showVal(this.value, 'w')"><br /><input type="text" id="labelw" oninput="rangew.value = labelw.value"><br />
		H<input id="rangeh" type="range" min="-100" max="1121" value="100" step="1"  oninput="showVal(this.value, 'h')"><br /><input type="text" id="labelh" oninput="rangeh.value = labelh.value"><br />
		<hr />
		<button id="buttonApply" onclick="saveXYWH()">
			Save position
		</button><br/>
		<hr />
		<button id="buttonClear" onclick="clearCanvas()">
			Clear Canvas
		</button><br/>
		<hr />
		<button id="buttonL1" onclick="reloadDivByLayer('base')">
			Base
		</button><br/>
		<button id="buttonL2" onclick="reloadDivByLayer('topSmall')">
			Top small
		</button><br/>
		<button id="buttonL3" onclick="reloadDivByLayer('topBig')">
			Top big
		</button><br/>
		<button id="buttonL4" onclick="reloadDivByLayer('botBig')">
			Bottom big
		</button><br/>
		<button id="buttonL5" onclick="reloadDivByLayer('topLeftSquare')">
			Top left square
		</button><br/>
		<button id="buttonL6" onclick="reloadDivByLayer('topRightSquare')">
			Top right square
		</button><br/>
		<button id="buttonL7" onclick="reloadDivByLayer('topLeftRect')">
			Top left rect
		</button><br/>
		<button id="buttonL8" onclick="reloadDivByLayer('topRightRect')">
			Top right rect
		</button><br/>
		<button id="buttonL9" onclick="reloadDivByLayer('middleUpLeftRect')">
			Middle up left rect
		</button><br/>
		<button id="buttonL10" onclick="reloadDivByLayer('middleUpRightRect')">
			Middle up right rect
		</button><br/>
		<button id="buttonL11" onclick="reloadDivByLayer('middleLeftSquare')">
			Middle left square
		</button><br/>
		<button id="buttonL12" onclick="reloadDivByLayer('middleRightSquare')">
			Middle right square
		</button><br/>
		<button id="buttonL13" onclick="reloadDivByLayer('middleBotLeftRect')">
			Middle bot left rect
		</button><br/>
		<button id="buttonL14" onclick="reloadDivByLayer('middleBotRightRect')">
			Middle bot right rect
		</button><br/>
		<button id="buttonL15" onclick="reloadDivByLayer('botLeftRect')">
			Bottom left rect
		</button><br/>
		<button id="buttonL16" onclick="reloadDivByLayer('botRightRect')">
			Bottom right rect
		</button><br/>
		<button id="buttonL17" onclick="reloadDivByLayer('botLeftSquare')">
			Bottom left square
		</button><br/>
		<button id="buttonL18" onclick="reloadDivByLayer('botRightSquare')">
			Bottom right square
		</button><br/>
		<button id="buttonL19" onclick="reloadDivByLayer('botLeftSmaill')">
			Bottom left small
		</button><br/>
		<button id="buttonL20" onclick="reloadDivByLayer('botRightSmall')">
			Bottom right small
		</button><br/>
		<hr />
		<button id="buttonCreate" onclick="createWindow()">
			Добавить в корзину
		</button><br/>
	</div>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>