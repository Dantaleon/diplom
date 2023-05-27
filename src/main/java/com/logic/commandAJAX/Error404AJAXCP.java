package com.logic.commandAJAX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.enums.CommandNameEnum;
import com.logic.enums.CommandEnumAJAX;

public class Error404AJAXCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("name", "nick3");
		request.getRequestDispatcher("/admin-table-view").forward(request, response);
	}
	
}
