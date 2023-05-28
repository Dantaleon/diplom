package com.logic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.CommandFactoryAJAX;
import com.logic.abstraction.EnumFactory;
import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;

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
		
		IEnumFactoryEntityAJAX specEnum = EnumFactory.getAjaxEnum(request);
		
		if (specEnum == null) {
			System.out.println("specEnum IS NULL");
		}
		
		ICommandProcessorAJAX commandProcessor = CommandFactoryAJAX.getCommand(request, specEnum);
		
		if (commandProcessor == null) {
			System.out.println("commandProcessor IS NULL");
		}
		
		commandProcessor.execute(request, response, specEnum);
		
	}
}
