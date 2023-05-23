package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.abstraction.CommandEnum;
import logic.abstraction.ICommandProcessor;
import utils.NextPage;

public class CatalogCP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage) {
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_REDIRECT);
		nextPage.setPage(CommandEnum.Catalog.getView());
		
		return nextPage;
	}

}
