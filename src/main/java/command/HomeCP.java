package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.abstraction.CommandEnum;
import logic.abstraction.ICommandProcessor;
import utils.NextPage;

public class HomeCP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response,
			NextPage nextPage) {
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_REDIRECT);
		nextPage.setPage(CommandEnum.Home.getView());
		
		System.out.println("HomeCP ended " + nextPage.getPage() + 
				" " + nextPage.getRedirectType());
		return nextPage;
	}

}
