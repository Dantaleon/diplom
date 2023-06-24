package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.WindowsPartsDAO;
import com.model.entity.WindowsParts;
import com.utils.NextPage;

public class TableWindowsPartsCP implements ICommandProcessorAJAX{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		WindowsPartsDAO windowsPartsDao = new WindowsPartsDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();
		
		windowsPartsList = windowsPartsDao.getAllRecords();
		
		request.setAttribute("windowsPartsList", windowsPartsList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableWindowsParts.name())).forward(request, response);
	}
}
