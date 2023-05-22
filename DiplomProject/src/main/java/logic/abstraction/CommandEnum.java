package logic.abstraction;

import command.*;

public enum CommandEnum {
	
	Home(new HomeCP()),
	ErrorPage404(new ErrorPage404CP());
	
	private ICommandProcessor command;
	
	CommandEnum(ICommandProcessor command){
		this.command = command;
	}
	
	public ICommandProcessor getCommand() {
		return command;
	}

	
}
