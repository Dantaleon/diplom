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
		
		ICommandProcessorAJAX commandProcessor = CommandFactoryAJAX.getCommand(request);
		
		//ajaxResponseText = commandProcessor.execute(request, response).toString();
		commandProcessor.execute(request, response);
		//out.println(ajaxResponseText);
		
	}
}
