package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import com.logic.enums.CommandNameEnum;

public final class CommandFactoryAJAX {
	
	private CommandFactoryAJAX() {
	}
	
	public static ICommandProcessorAJAX getCommand(HttpServletRequest request, IEnumFactoryEntityAJAX commandEnum) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");

		try {
			
			ICommandProcessorAJAX commandProcessor = commandEnum.getMyCommand(command);
			
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
