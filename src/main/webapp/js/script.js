const canvas = document.getElementById('canvas1');
const ctx = canvas.getContext('2d');
const divImg = document.getElementById('myImages');

const labelx = document.getElementById('labelx');
const labely = document.getElementById('labely');

var lastClicked;

const allItems = [];

const divItems = [];

window.onload = function(){

	console.log('ready fired');
	ready1();
	
}

class WindowPart {

	constructor(imagePath, x, y, w, h, level){

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

	for (let i = 0; i < allItems.length; i++){
		if (allItems[i].hasLayerClass(level)){
			addPartToDiv(allItems[i], divImg);
		}
	}
}
function ready1(){
	//let window1 = new WindowPart("images/window1.png", 100, 200, 200, 200, 1);
	//allItems.push(window1);
	//let window2 = new WindowPart("images/window2.png", 300, 200, 200, 200, 2);
	//allItems.push(window2);
	//let window3 = new WindowPart("images/window3.png", 500, 200, 200, 200, 3);
	//allItems.push(window3);

	let base = new WindowPart("../images_native/base1.png", 720, 154, 720*1/2, 1121*1/2, 1)
	allItems.push(base);
	base = new WindowPart("../images_native/base2.png", 720, 154, 720*1/2, 1121*1/2, 1)
	allItems.push(base);
	allItems.push(new WindowPart("../images_native/top1.png", 637, 0, 720*1/2, 1121*1/2, 2));
	allItems.push(new WindowPart("../images_native/top2.png", 637, 0, 720*1/2, 1121*1/2, 2));
	allItems.push(new WindowPart("../images_native/bottom1.png", 718, 407, 720*1/2, 1121*1/2, 2));
	allItems.push(new WindowPart("../images_native/bottom2.png", 718, 407, 720*1/2, 1121*1/2, 2));
	allItems.push(new WindowPart("../images_native/side1.png", 688, 115, 720*1/2, 1121*1/2, 3));
	allItems.push(new WindowPart("../images_native/side2.png", 688, 115, 720*1/2, 1121*1/2, 3));
	allItems.push(new WindowPart("../images_native/side1.png", 879, 113, 720*1/2, 1121*1/2, 3));
	allItems.push(new WindowPart("../images_native/side2.png", 879, 113, 720*1/2, 1121*1/2, 3));
}

