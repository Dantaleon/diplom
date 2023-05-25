package com.logic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.CommandEnum;
import com.logic.abstraction.CommandFactory;
import com.logic.abstraction.ICommandProcessor;
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
				
		NextPage nextPage = new NextPage(CommandEnum.ErrorPage404.getView(), NextPage.REDIRECT_TYPE_REDIRECT);
		
		ICommandProcessor commandProcessor = CommandFactory.getCommand(request);
		
		nextPage = commandProcessor.execute(request, response, nextPage);
		
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
