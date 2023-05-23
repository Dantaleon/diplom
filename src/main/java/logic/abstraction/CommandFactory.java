package logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import command.ErrorPage404CP;

public final class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static ICommandProcessor getCommand(HttpServletRequest request) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");
			
//		second method try
		try {
			System.out.println(command);
			System.out.println(CommandEnum.valueOf(command));
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
