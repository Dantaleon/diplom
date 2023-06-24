package com.logic.commandAJAX;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.MaterialTypeDAO;
import com.model.dao.PieceDAO;
import com.model.dao.PieceTypeDAO;
import com.model.entity.MaterialType;
import com.model.entity.Piece;
import com.model.entity.PieceType;
import com.utils.NextPage;

public class TablePieceCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {

		String subCommand = (String) request.getParameter("sub");

		if (subCommand == null || subCommand.trim().equals("")) {
			PieceDAO pieceDao = new PieceDAO();
			MaterialTypeDAO materialTypeDao = new MaterialTypeDAO();
			PieceTypeDAO pieceTypeDao = new PieceTypeDAO();

			response.setContentType(NextPage.CONTENT_TYPE_JSP);

			ArrayList<Piece> pieceList = new ArrayList<Piece>();
			ArrayList<MaterialType> materialTypeList = new ArrayList<MaterialType>();
			ArrayList<PieceType> pieceTypeList = new ArrayList<PieceType>();

			pieceList = pieceDao.getAllRecords();
			materialTypeList = materialTypeDao.getAllRecords();
			pieceTypeList = pieceTypeDao.getAllRecords(); // try delete arrays

			request.setAttribute("pieceList", pieceList);
			request.setAttribute("materialTypeList", materialTypeList);
			request.setAttribute("pieceTypeList", pieceTypeList);

			request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TablePiece.name())).forward(request,
					response);
		} else if (subCommand.equals("postNew")) {

			MaterialTypeDAO mtypeDao = new MaterialTypeDAO();
			PieceTypeDAO ptypeDao = new PieceTypeDAO();
			PieceDAO pieceDao = new PieceDAO();
			
			String name = null;
			InputStream imageInpStream = null;
			int picx = 0; int picy = 0;
			int picw = 200; int pich = 200;
			int picdw = 200; int picdh = 200;
			double cost = 0;
			int realw = 0;
			int realh = 0;
			int realth = 0;
			BigDecimal mtypeid = null;
			BigDecimal ptypeid = null;
			
			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				List<FileItem> items = upload.parseRequest(request);
				
				for (FileItem item : items) {
			        if (item.isFormField()) {
			            String fieldName = item.getFieldName();
			            String fieldValue = item.getString();
			            if (fieldName.equals("name")) {
			                name = fieldValue;
			            } else if (fieldName.equals("picx")) {
			            	picx = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("picy")) {
			            	picy = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("picw")) {
			            	picw = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("pich")) {
			            	pich = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("picdw")) {
			            	picdw = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("picdh")) {
			            	picdh = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("cost")) {
			            	 cost = Double.parseDouble(fieldValue);
			            } else if (fieldName.equals("realw")) {
			                realw = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("realh")) {
			            	realh = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("realth")) {
			            	realth = Integer.parseInt(fieldValue);
			            } else if (fieldName.equals("materialType")){
			            	mtypeid = new BigDecimal(fieldValue);
			            } else if (fieldName.equals("pieceType")) {
			            	ptypeid = new BigDecimal(fieldValue);
			            }
			            
			        } else {
			            String fieldName = item.getFieldName();

			            if (fieldName.equals("pieceImg")) {
			            	imageInpStream = item.getInputStream();
			            }
			        }
				
				}
				
				// Read the data from the InputStream into a byte array
			    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			    byte[] buffer = new byte[4096];
			    int bytesRead;
			    while ((bytesRead = imageInpStream.read(buffer)) != -1) {
			        outputStream.write(buffer, 0, bytesRead);
			    }
			    byte[] imageBytes = outputStream.toByteArray();
			    
			    // Convert the byte array to a base64 string
			    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				
			    System.out.println(mtypeid);
			    System.out.println("ptypeid:" + ptypeid);
			    
				Piece piece = new Piece(
						name,
						base64Image, 
						picx, picy, 
						picw, pich, 
						picdw, picdh, 
						cost,
						realw,
						realh,
						realth,
						mtypeDao.getRecordByPK(mtypeid),
						ptypeDao.getRecordByPK(ptypeid)
				); 

				pieceDao.newRecord(piece);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			  response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			  response.getWriter().write("new piece created");
			 
			return;
		}else if (subCommand.equals("saveXYWH")) {
			
			InputStream requestBodyStream = request.getInputStream();
	        String requestBody = new BufferedReader(
	            new InputStreamReader(requestBodyStream))
	            .lines()
	            .collect(Collectors.joining("\n"));
	        
	        System.out.println(requestBody);
	        
	        PieceDAO pieceDao = new PieceDAO();
	        
	        int rowUpd = pieceDao.saveXYWHByJsonStr(requestBody);
	        
	        response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("rowUpd:" + rowUpd);
			return;
		}else if (subCommand.equals("editRecord")) { 
			
			System.out.println(request.getParameter("id"));
			BigDecimal editId = new BigDecimal(request.getParameter("id"));
			System.out.println("edit piece with id: " + editId);
			
			MaterialTypeDAO mtypeDao = new MaterialTypeDAO();
			PieceTypeDAO ptypeDao = new PieceTypeDAO();
			PieceDAO pieceDao = new PieceDAO();
			
			Piece editPiece = pieceDao.getRecordByPK(editId);
			
			InputStream imageInpStream = null;
			
			int rowEdit = -1;
			
			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				List<FileItem> items = upload.parseRequest(request);
				
				for (FileItem item : items) {
			        if (item.isFormField()) {
			            String fieldName = item.getFieldName();
			            String fieldValue = item.getString();
			            if (fieldName.equals("name")) {
			                editPiece.setName(fieldValue);
			            } else if (fieldName.equals("picx")) {
			            	 editPiece.setPicx(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("picy")) {
			            	 editPiece.setPicy(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("picw")) {
			            	 editPiece.setPicw(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("pich")) {
			            	 editPiece.setPich(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("picdw")) {
			            	 editPiece.setDefaultw(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("picdh")) {
			            	 editPiece.setDefaulth(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("cost")) {
			            	 editPiece.setCost(Double.parseDouble(fieldValue));
			            } else if (fieldName.equals("realw")) {
			            	 editPiece.setRealw(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("realh")) {
			            	editPiece.setRealh(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("realth")) {
			            	editPiece.setRealth(Integer.parseInt(fieldValue));
			            } else if (fieldName.equals("materialType")){
			            	editPiece.setMaterialType(mtypeDao.getRecordByPK(new BigDecimal(fieldValue)));
			            } else if (fieldName.equals("pieceType")) {
			            	editPiece.setPieceType(ptypeDao.getRecordByPK(new BigDecimal(fieldValue)));
			            }
			            
			        } else {
			            String fieldName = item.getFieldName();

			            if (fieldName.equals("pieceImg")) {
			            	imageInpStream = item.getInputStream();
			            }
			        }
				
				}
				
				// Read the data from the InputStream into a byte array
			    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			    byte[] buffer = new byte[4096];
			    int bytesRead;
			    while ((bytesRead = imageInpStream.read(buffer)) != -1) {
			        outputStream.write(buffer, 0, bytesRead);
			    }
			    byte[] imageBytes = outputStream.toByteArray();
			    
			    // Convert the byte array to a base64 string
			    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			    
			    editPiece.setBase64Img(base64Image); // image SET
			    
			    rowEdit = pieceDao.editRecord(editPiece);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("rowEdit:" + rowEdit);
			return;
			
		}else if (subCommand.equals("deleteRecord")) {
			
			BigDecimal deleteId = new BigDecimal((String) request.getParameter("id"));
			System.out.println("delete piece with id: " + deleteId);
			
			PieceDAO pieceDao = new PieceDAO();
			int rowDel = pieceDao.deleteRecord(deleteId);
			
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("rowDel:" + rowDel);
			return;
		}else{
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("something goes wrong");
			return;
		}
	}
}
