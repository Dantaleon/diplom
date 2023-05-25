package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;

public class CommandFactoryAJAX {
	
	private CommandFactoryAJAX() {
	}
	
	public static ICommandProcessorAJAX getCommand(HttpServletRequest request) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");

		try {
			ICommandProcessorAJAX commandProcessor = CommandEnumAJAX.valueOf(command).getCommand();
			return commandProcessor;
			
		}catch(IllegalArgumentException iae) {
			iae.printStackTrace();
			return CommandEnumAJAX.ErrorPage404.getCommand();
			
		}catch(Exception e) {
			e.printStackTrace();
			return CommandEnumAJAX.ErrorPage404.getCommand();
		}
	}
}
