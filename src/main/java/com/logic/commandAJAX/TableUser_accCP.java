package com.logic.commandAJAX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.utils.NextPage;

public class TableUser_accCP implements ICommandProcessorAJAX {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return "Таблица User_acc (data)";
	}

	
}
