package com.logic.commandAJAX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;

public class Error404AJAXCP implements ICommandProcessorAJAX {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "Ошибка 404";
	}
	
}
