package com.logic.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandEnumGuest;
import com.logic.enums.CommandNameEnum;

public class LogoutCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {
		try {
			request.getSession().invalidate();
			request.setAttribute("noError", "Вы успешно вышли из аккаунта");
		} catch(IllegalStateException ise) {
			System.out.println("Сессия уже invalidated");
		}
		
		response.sendRedirect("/preIndex.jsp");
	}

}
