package com.logic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.CommandFactoryAJAX;
import com.logic.abstraction.ICommandProcessorAJAX;

@WebServlet("/ControllerAJAX")
public class ControllerAJAX extends HttpServlet {
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
       
    public ControllerAJAX() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		
		PrintWriter out = response.getWriter();
		
		String ajaxResponseText = "";
		
		ICommandProcessorAJAX commandProcessor = CommandFactoryAJAX.getCommand(request);
		
		ajaxResponseText = commandProcessor.execute(request, response);
		
		out.println(ajaxResponseText);
		
		out.close();
		
		
	}
}
