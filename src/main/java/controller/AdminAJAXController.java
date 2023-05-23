package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.abstraction.CommandEnum;
import logic.abstraction.CommandFactory;
import logic.abstraction.ICommandProcessor;
import utils.NextPage;

@WebServlet("/AdminAJAXController")
public class AdminAJAXController extends HttpServlet {

    public AdminAJAXController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		NextPage nextPage = new NextPage("/index.jsp", NextPage.REDIRECT_TYPE_REDIRECT);
		
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
