package com.logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.CommandEnum;
import com.logic.abstraction.ICommandProcessor;
import com.utils.NextPage;

public class ErrorPage404CP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, 
			NextPage nextPage) {
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(CommandEnum.ErrorPage404.getView());
		
		return nextPage;
	}

}
