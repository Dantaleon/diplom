<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.Piece" %>
<table>
  <thead>
    <tr>
      <th class="alignCenter">ID</th>
      <th class="alignCenter">Name</th>
      <th class="alignCenter">Картинка</th>
      <th class="alignCenter">[x, y]</th>
      <th class="alignCenter">[w, h]</th>
      <th class="alignCenter">default [w, h]</th>
      <th class="alignCenter">Стоимость</th>
      <th class="alignCenter">Наст. [шир.,выс.,толщ.]</th>
      <th class="alignCenter">[ID]Тип материала</th>
      <th class="alignCenter">[ID]Тип кусочка</th>
      <th class="alignCenter">Действие</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="piece" items="${pieceList}">
      <tr>
        <td class="alignCenter">${piece.id}</td>
        <td class="alignLeft">${piece.name}</td>
        <td class="alignCenter pieceImage"><img src="data:image/png;base64,${piece.base64Img}"/></td>
        <td class="alignCenter">[${piece.picx}, ${piece.picy}]</td>
        <td class="alignCenter">[${piece.picw}, ${piece.pich}]</td>
        <td class="alignCenter">[${piece.defaultw}, ${piece.defaulth}]</td>
        <td class="alignRight">${piece.cost}</td>
        <td class="alignCenter">[${piece.realw}, ${piece.realh}, ${piece.realth}]</td>
        <td class="alignCenter">[${piece.materialType.id}]${piece.materialType.name}</td>
        <td class="alignCenter">[${piece.pieceType.id}] ${piece.pieceType.name}</td>
        <td class="alignCenter"><button class="editBtn"onclick="editPieceRecord(${piece.id})">Редактировать</button></td>
        <td class="alignCenter"><button class="deleteBtn"onclick="deletePieceRecord(${piece.id})">Удалить</button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<div>
	<button class="createBtn" onclick="createPieceRecord()">Создать</button>
</div>

<div id="editPieceRecord" hidden="hidden">
	<table>
	<tr>
		<td>
			<label for="name">Name*: </label>
		</td>
		<td>
			<input id="inpName" type="text" name="name" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="pieceImg">Image*: </label>
		</td>
		<td>
			<input type="file" name="pieceImg" />
		</td>
	</tr>
	<tr>
		<td>
			<label for="picx">PicX: </label>
		</td>
		<td>
			<input id="inpPicx" type="number" name="picx" required="required"></input>
		</td>
		<td>
			<label for="picy">PicY: </label>
		</td>
		<td>
			<input id="inpPicy" type="number" name="picy" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="picw">PicW: </label>
		</td>
		<td>
			<input id="inpPicw" type="number" name="picw" required="required"></input>
		</td>
		<td>
			<label for="pich">PicH: </label>
		</td>
		<td>
			<input id="inpPich" type="number" name="pich" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="picdw">PicDefW: </label>
		</td>
		<td>
			<input id="inpPicdefw" type="number" name="picdw" required="required"></input>
		</td>
		<td>
			<label for="picdh">PicDefH: </label>
		</td>
		<td>
			<input id="inpPicdefh" type="number" name="picdh" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="cost">Cost: </label>
		</td>
		<td>
			<input type="number" name="cost" />
		</td>
	</tr>
	<tr>
		<td>
			<label for="realw">RealW: </label>
		</td>
		<td>
			<input id="inpPicrw" type="number" name="realw" required="required"></input>
		</td>
		<td>
			<label for="realh">RealH: </label>
		</td>
		<td>
			<input id="inpPicrh" type="number" name="realh" required="required"></input>
		</td>
		<td>
			<label for="realth">RealTH: </label>
		</td>
		<td>
			<input id="inpPicrth" type="number" name="realth" required="required"></input>
		</td>
	</tr>
	<tr>
		<td>
			<label for="materialType">MaterialType*: </label>
		</td>
		<td>
			<select name="materialType">
			<c:forEach var="materialType" items="${materialTypeList}">
				<option value="${materialType.id}">${materialType.name}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<label for="pieceType">PieceType*: </label>
		</td>
		<td>
			<select name="pieceType">
			<c:forEach var="pieceType" items="${pieceTypeList}">
				<option value="${pieceType.id}">${pieceType.name}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
	<td>
		<button class="createBtn" id="btnCreatePiece" onclick="createPieceRecordSubmit()">Создать</button>
		<button class="editBtn" id="btnEditPiece" onclick="editPieceRecordSubmit()">Редактировать</button>
	</td>
	<td>
		<button id="btnClear" onclick="clearPieceEdit()">Очистить</button>
	</td>
	</tr>
	</table>
</div>