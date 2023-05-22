package logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import command.ErrorPage404CP;

public final class CommandFactory {
	
	private CommandFactory() {
	}
	
	public static ICommandProcessor getCommand(HttpServletRequest request) {
		
		String command = null;
		
		command = request.getParameter("ActionCommand");
		
		String StrCommandClass = "command.ErrorPage404CP";
		
		if (command != null) {
			
			StrCommandClass = "command." + command + "CP";
		
		} else {
			System.out.println("Action command is null. sending error page 404");
		}
		
		try {
			Class myClass = Class.forName(StrCommandClass);
			
			ICommandProcessor commandProcessor = (ICommandProcessor) myClass.newInstance();
			
			return commandProcessor;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return CommandEnum.ErrorPage404.getCommand();
		}
		
	}
}
