package com.logic.commandAJAX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.abstraction.IEnumFactoryEntityAJAX;

public class TableRoleCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {
		
		
		request.setAttribute("name", "nick");
		request.getRequestDispatcher("/tableViewTemplates/RoleTemplate.jsp").forward(request, response);
	}

}
