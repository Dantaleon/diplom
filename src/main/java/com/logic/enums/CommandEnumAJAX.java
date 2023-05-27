package com.logic.enums;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.commandAJAX.*;

public enum CommandEnumAJAX {
	
	TableUser_acc(new TableUser_accCP()),
	TableRole(new TableRoleCP()),
	ErrorPage404(new Error404AJAXCP());
	
	private ICommandProcessorAJAX command;
	
	CommandEnumAJAX(ICommandProcessorAJAX command){
		this.command = command;
	}
	
	public ICommandProcessorAJAX getCommand() {
		return command;
	}
	
}
