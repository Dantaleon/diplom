package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import com.logic.command.ErrorPage404CP;
import com.logic.enums.CommandNameEnum;

public final class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static ICommandProcessor getCommand(HttpServletRequest request, IEnumFactoryEntity commandEnum) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");

		try {
			
			ICommandProcessor commandProcessor = commandEnum.getMyCommand(command);
			
			return commandProcessor;
			
		}catch(IllegalArgumentException iae) {
			iae.printStackTrace();
			return commandEnum.getMyCommand(CommandNameEnum.ErrorPage404.name());
			
		}catch(Exception e) {
			e.printStackTrace();
			return commandEnum.getMyCommand(CommandNameEnum.ErrorPage404.name());
		}
	}
}
