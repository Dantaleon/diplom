const canvas = document.getElementById('canvas1');
const ctx = canvas.getContext('2d');
const divImg = document.getElementById('myImages');

const labelx = document.getElementById('labelx');
const labely = document.getElementById('labely');
const lablew = document.getElementById('labelw');
const lableh = document.getElementById('labelh');

var lastClicked;

const allItems = [];

const divItems = [];

window.onload = function(){

	console.log('ready fired');
	ready1();
	
	
}



class WindowPart {

	constructor(id, imagePath, x, y, w, h, level){
		
		this.id = id;
		this.image = new Image(w, h);
		this.image.src = imagePath;
		this.level = level;
		let self = this;
		this.addLayerClass(level);
		this.image.addEventListener('click', function(){ addPartToDraw(self)}, false);
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;

	}

	hasLayerClass(level) {
		return this.image.classList.contains('layer' + level);
	}

	addLayerClass(level) {
		this.image.classList.add('layer' + level);
	}
	removeLayerClass(level) {
		this.image.classList.remove('layer' + level);
	}
	draw() {
		console.log("draw is fired");
		ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
		
	}
	moveX(value) {
		this.x = value;
	}
	moveY(value) {
		this.y = value;
	}
	resizeW(value) {
		this.width = value;
	}
	resizeH(value) {
		this.height = value;
	}
}

function showVal(value, axis) {
	if (axis == 'x') {
		console.log('moveX via showval');
		labelx.value = value;
		lastClicked.moveX(value);
	}
	if (axis == 'y') {
		labely.value = value;
		lastClicked.moveY(value);
	}
	if (axis == 'w') {
		labelw.value = value;
		lastClicked.resizeW(value);
	}
	if (axis == 'h') {
		labelh.value = value;
		lastClicked.resizeH(value);
	}
	drawLevels();
}

function drawLevels(){
	ctx.clearRect(0,0, canvas.width, canvas.height);
	if (divItems.length > 0){
		for (let i = 0; i < divItems.length; i++){
			if (divItems[i].hasLayerClass(1) ){
				divItems[i].draw();
			}
		}
		for (let i = 0; i < divItems.length; i++){
			if (divItems[i].hasLayerClass(2) ){
				divItems[i].draw();
			}
		}
		for (let i = 0; i < divItems.length; i++){
			if (divItems[i].hasLayerClass(3) ){
				divItems[i].draw();
			}
		}
	}
}

function addPartToDiv(part, div){
	div.appendChild(part.image);
}

function isArrayHasImage(array, image){
	let isFind = false;
	for (let i = 0; i < array.length; i++){
		
		if (image === divItems[i].image) {
			
			isFind = true;
		}
	}
	return isFind;
}

function addPartToDraw(windowPart) {

	if (!(isArrayHasImage(divItems, windowPart.image) ) ){
		
		divItems.push(windowPart);
		lastClicked = divItems[divItems.length - 1];
	}
	
	drawLevels();
}

function removeChild(windowPart, div) {

	let index = divItems.indexOf(windowPart);

	if (index > -1) {
		divItems.splice(index, 1);
	}
	
}
function reloadDivByLayer(level){
	
	divImg.innerHTML = "";
	
	xhr = new XMLHttpRequest();
	xhr.open('GET', '/ControllerAJAX?ActionCommand=PieceUCP&sub=getPiecesByType&type=' + level, true);
	
	xhr.onreadystatechange = function() {
	    if(xhr.readyState == 4 && xhr.status == 200) {
	      
			var jsonString = this.responseText;
    		try {
			  var jsonObject = JSON.parse(jsonString);
			  drawJsonToDiv(jsonObject);
			} catch (e) {
			  console.log("Error parsing JSON");
			}
	    }
	}
	
	xhr.send();
	
}
function drawJsonToDiv(jsonArray) {
	
	for (var i = 0; i < jsonArray.length; i++) {
  		
  		var obj = jsonArray[i];
  		var winPart = new WindowPart(obj.id,
			  'data:image/png;base64,' + obj.image, obj.picx, obj.picy,
  			obj.picw, obj.pich, 1);
  		
  		addPartToDiv(winPart, divImg);
  		
  	}
	
}

function saveXYWH() {
	
	let pieceId = lastClicked.id;
	
	xhr = new XMLHttpRequest();
	xhr.open('POST', '/ControllerAJAX?ActionCommand=TablePiece&sub=saveXYWH&id=' + pieceId , true);
	xhr.setRequestHeader('Content-type', 'application/json');
	
	xhr.onreadystatechange = function() {
	    if(xhr.readyState == 4 && xhr.status == 200) {
	      
			var strResp = this.responseText;
    		console.log(strResp);
	    }
	}
	
	let jsonObj = {
		id: pieceId,
		picx: lastClicked.x,
		picy: lastClicked.y,
		picw: lastClicked.width,
		pich: lastClicked.height
	};
	
	let strJson = JSON.stringify(jsonObj);
	
	xhr.send(strJson);
}

function ready1(){
	
}
function clearCanvas() {
	ctx.clearRect(0,0, canvas.width, canvas.height);
	divItems.length = 0;
}
function createWindow() {
	var windowParts = [];
	for (let i = 0; i < divItems.length; i++) {
		windowParts[i] = {id: divItems[i].id};

	}
	console.log(windowParts);
	let jsonWindowParts = JSON.stringify(windowParts);
	const wxhr = new XMLHttpRequest();
	wxhr.open('POST','/Controller?ActionCommand=MyCart&sub=addFromDesign');
	wxhr.setRequestHeader('Content-Type', 'application/json');
	wxhr.send(jsonWindowParts);
}