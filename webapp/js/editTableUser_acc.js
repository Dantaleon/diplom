var link = document.createElement('link');
link.rel = 'stylesheet';
link.type = 'text/css';
link.href = '/css/DBTable.css';
document.head.appendChild(link);

function createRecord() {
    showEditDiv(true);
    showButtons('create');
}
function createRecordSubmit() {
	var id = document.getElementById('inpId').value;
	var nickname = document.getElementById('inpNickname').value;
	var email = document.getElementById('inpEmail').value;
	var password = document.getElementById('inpPassword').value;
	
	var params = '&sub=postNew&newId=' + id + '&newNickname=' + nickname
		+ '&newEmail=' + email + '&newPassword=' + password;
	
	xhr = new XMLHttpRequest();
	xhr.open('POST', '/ControllerAJAX?ActionCommand=TableUser_acc', true);

	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xhr.onreadystatechange = function() {
	    if(xhr.readyState == 4 && xhr.status == 200) {
	      
			let contentType = xhr.getResponseHeader('Content-Type');
			console.log(xhr.responseText);
	    }
	}
	
	xhr.send(params);
}

function editRecord(key) {
    showEditDiv(true);
    showButtons('edit');
    var retr = retrieveRecordByKey(key);
}
function editRecordSubmit() {
	
}
function deleteRecord() {
	if (confirm('Вы действительно хоитите удалить запись пользователя?')) {
		deleteRecordSubmit();
	} else {
		console.log('Удаление отменено');
	}
}
function deleteRecordSubmit(){
	 
}
function clearEdit() {
	
}
function retrieveRecordByKey(key){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/ControllerAJAX?ActionCommand=TableUser_acc&sub=getById&id=' + key, true);
	xhr.onload = function() {
		if (this.status === 200) {
			var jsonString = this.responseText;
    		try {
			  var jsonObject = JSON.parse(jsonString);
			  console.log(jsonObject);
			} catch (e) {
			  console.log("Error parsing JSON");
			}
		}
	};
	xhr.send();
}

function showEditDiv(isShow) {
    let editDiv = document.getElementById('editRecord');
    if (isShow) { editDiv.style.display = 'block'; }
    else { editDiv.style.display = 'none'; }
}

function showButtons(mode) {
    let btnCreate = document.getElementById('btnCreate');
    let btnEdit = document.getElementById('btnEdit');
    if (mode === 'create') {
        btnCreate.style.display = 'block';
        btnEdit.style.display = 'none';
    }
    if (mode === 'edit') {
        btnCreate.style.display = 'none';
        btnEdit.style.display = 'block';
    }
}