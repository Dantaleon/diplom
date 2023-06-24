package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.model.dao.PieceDAO;
import com.model.entity.Piece;

public class PieceUCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		String subCommand = (String) request.getParameter("sub");
		String pieceType = (String) request.getParameter("type");
		System.out.println(subCommand + " " + pieceType);
		
		if (subCommand == null || subCommand.trim().equals("")) {
			
			return;
		}
		
		if (subCommand.equals("getPiecesByType") && pieceType != null 
				&& !pieceType.trim().equals("")) {
			
			PieceDAO pieceDao = new PieceDAO();
			ArrayList<Piece> pieceList = new ArrayList<Piece>();
			
			pieceList = pieceDao.getRecordsByType(pieceType);
			
			String pieceJson = pieceDao.pieceListToJson(pieceList);
			
			System.out.println(pieceJson);
			response.getWriter().write(pieceJson);
			return;
		}
	}
}
