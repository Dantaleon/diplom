package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.*;

public enum CommandNameEnum {
	
	Home("Home"),
	ErrorPage404("ErrorPage404"),
	Catalog("Catalog"),
	Login("Login"),
	Registration("Registration"),
	Contact("Contact"),
	About("About"),
	AdminTableView("AdminTableView");

	private String name;
	
	CommandNameEnum(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}