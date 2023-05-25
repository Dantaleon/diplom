package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommandProcessorAJAX {
	
	String execute(HttpServletRequest request, HttpServletResponse response);
}
