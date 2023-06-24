var link = document.createElement('link');
link.rel = 'stylesheet';
link.type = 'text/css';
link.href = '/css/DBTable.css';
document.head.appendChild(link);

function createPieceRecord() {
    showEditDiv(true);
    showButtons('create');
}
function editPieceRecord(id) {
	showEditDiv(true);
    showButtons('edit');
    document.getElementById('btnEditPiece').innerHTML = 'Редактировать ' + id;
    document.getElementById('btnEditPiece').onclick = () => editPieceRecordSubmit(id);
}
function createPieceRecordSubmit() {
	
	var creationForm = createForm('/ControllerAJAX?ActionCommand=TablePiece&sub=postNew');

    var xhr = new XMLHttpRequest();
    xhr.open(creationForm.method, creationForm.action, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log(xhr.responseText);
        } else {
            console.error(xhr.status);
        }
    };

    xhr.send(new FormData(creationForm));
		
}
function editPieceRecordSubmit(pieceId) {
	
	var editForm = createForm('/ControllerAJAX?ActionCommand=TablePiece&sub=editRecord&id=' + pieceId);

    var xhr = new XMLHttpRequest();
    xhr.open(editForm.method, editForm.action, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log(xhr.responseText);
        } else {
            console.error(xhr.status);
        }
    };

    xhr.send(new FormData(editForm));
}

function showEditDiv(isShow) {
    let editDiv = document.getElementById('editPieceRecord');
    if (isShow) { editDiv.style.display = 'block'; }
    else { editDiv.style.display = 'none'; }
}

function showButtons(mode) {
    let btnCreate = document.getElementById('btnCreatePiece');
    let btnEdit = document.getElementById('btnEditPiece');
    if (mode === 'create') {
        btnCreate.style.display = 'block';
        btnEdit.style.display = 'none';
    }
    if (mode === 'edit') {
		document.getElementById('btnEditPiece').innerHTML = 'Редактировать';
        btnCreate.style.display = 'none';
        btnEdit.style.display = 'block';
    }
}

function deletePieceRecord(pieceId) {
	
	xhr = new XMLHttpRequest();
	xhr.open('POST', '/ControllerAJAX?ActionCommand=TablePiece&sub=deleteRecord&id=' + pieceId, true);

	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xhr.onreadystatechange = function() {
	    if(xhr.readyState == 4 && xhr.status == 200) {
	      
			console.log(xhr.responseText);
	    }
	}
	
	xhr.send();
}

function createForm(action) {
	
	var form = document.createElement('form');
    form.method = 'POST';
    form.action = action;
    form.enctype = 'multipart/form-data';
    
    // Retrieve input elements
    var inpName = document.getElementsByName('name')[0].cloneNode(true);
    var inpImage = document.getElementsByName('pieceImg')[0].cloneNode(true);
    
    var inpPicx = document.getElementsByName('picx')[0].cloneNode(true);
    var inpPicy = document.getElementsByName('picy')[0].cloneNode(true);
    var inpPicw = document.getElementsByName('picw')[0].cloneNode(true);
    var inpPich = document.getElementsByName('pich')[0].cloneNode(true);
    var inpPicdw = document.getElementsByName('picdw')[0].cloneNode(true);
    var inpPicdh = document.getElementsByName('picdh')[0].cloneNode(true);
    var inpCost = document.getElementsByName('cost')[0].cloneNode(true);
 
    var inpRealw = document.getElementsByName('realw')[0].cloneNode(true);
    var inpRealh = document.getElementsByName('realh')[0].cloneNode(true);
    var inpRealth = document.getElementsByName('realth')[0].cloneNode(true);
    
    var inpMaterialTypeId = document.getElementsByName('materialType')[0].selectedOptions[0].value;
    var hiddenMaterialType = document.createElement('input');
	hiddenMaterialType.type = 'hidden';
	hiddenMaterialType.name = 'materialType';
	hiddenMaterialType.value = inpMaterialTypeId;
	
    var inpPieceTypeId = document.getElementsByName('pieceType')[0].selectedOptions[0].value;
	var hiddenPieceType = document.createElement('input');
	hiddenPieceType.type = 'hidden';
	hiddenPieceType.name = 'pieceType';
	hiddenPieceType.value = inpPieceTypeId;
	
    // Append input elements to the form
    form.appendChild(inpName);
    form.appendChild(inpImage);
    
    form.appendChild(inpPicx);
    form.appendChild(inpPicy);
    form.appendChild(inpPicw);
    form.appendChild(inpPich);
    form.appendChild(inpPicdw);
    form.appendChild(inpPicdh);
    form.appendChild(inpCost);
    
    form.appendChild(inpRealw);
    form.appendChild(inpRealh);
    form.appendChild(inpRealth);
    form.appendChild(hiddenMaterialType);
    form.appendChild(hiddenPieceType);
    
    return form;
}
