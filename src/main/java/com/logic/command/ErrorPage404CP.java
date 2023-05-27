package com.logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.utils.NextPage;

public class ErrorPage404CP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, 
			NextPage nextPage, IEnumFactoryEntity specEnum) {
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.ErrorPage404.getName()));
		
		return nextPage;
	}

}
