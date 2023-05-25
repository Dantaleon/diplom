package com.logic.abstraction;

import com.logic.command.*;

public enum CommandEnum {
	
	Home(new HomeCP(), "/index.jsp"),
	ErrorPage404(new ErrorPage404CP(), "/404.jsp"),
	Catalog(new CatalogCP(), "/show-images.jsp");
	
	private ICommandProcessor command;
	private String view;
	
	CommandEnum(ICommandProcessor command, String view){
		this.command = command;
		this.view = view;
	}
	
	public ICommandProcessor getCommand() {
		return command;
	}
	
	public String getView() {
		return view;
	}
	
}
