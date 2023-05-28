package com.logic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.CommandFactory;
import com.logic.abstraction.EnumFactory;
import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.logic.enums.CommandEnumAdmin;
import com.logic.enums.CommandEnumGuest;
import com.logic.enums.RoleEnums;
import com.utils.Resources;
import com.utils.NextPage;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		NextPage nextPage = new NextPage(CommandEnumGuest.Home.getView(), NextPage.REDIRECT_TYPE_FORWARD);
		
		IEnumFactoryEntity specEnum = EnumFactory.getSimpleEnum(request);

		ICommandProcessor commandProcessor = CommandFactory.getCommand(request, specEnum);
		
		nextPage = commandProcessor.execute(request, response, nextPage, specEnum);
		
		dispatch(request, response, nextPage);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, 
			NextPage nextPage) throws ServletException, IOException {

		if (nextPage.getRedirectType().equals(NextPage.REDIRECT_TYPE_FORWARD)) {
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextPage.getPage());
			dispatcher.forward(request, response);
		}
		else if (nextPage.getRedirectType().equals(NextPage.REDIRECT_TYPE_REDIRECT)) {
			
			response.sendRedirect(nextPage.getPage());
		} else {
			System.out.println("NextPage type not defined properly");
		}
	}
}
