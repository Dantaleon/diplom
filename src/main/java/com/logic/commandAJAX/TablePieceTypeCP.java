package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.PieceTypeDAO;
import com.model.entity.PieceType;
import com.utils.NextPage;

public class TablePieceTypeCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		PieceTypeDAO pieceTypeDao = new PieceTypeDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<PieceType> pieceTypeList = new ArrayList<PieceType>();
		
		pieceTypeList = pieceTypeDao.getAllRecords();
		
		request.setAttribute("pieceTypeList", pieceTypeList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TablePieceType.name())).forward(request, response);
	}
}
