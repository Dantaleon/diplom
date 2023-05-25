package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import com.logic.command.ErrorPage404CP;

public final class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static ICommandProcessor getCommand(HttpServletRequest request) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");

		try {
			ICommandProcessor commandProcessor = CommandEnum.valueOf(command).getCommand();
			return commandProcessor;
			
		}catch(IllegalArgumentException iae) {
			iae.printStackTrace();
			return CommandEnum.ErrorPage404.getCommand();
			
		}catch(Exception e) {
			e.printStackTrace();
			return CommandEnum.ErrorPage404.getCommand();
		}
	}
}
