package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.AboutCP;
import com.logic.command.AdminTableViewCP;
import com.logic.command.CatalogCP;
import com.logic.command.ContactCP;
import com.logic.command.ErrorPage404CP;
import com.logic.command.HomeCP;
import com.logic.command.LoginCP;
import com.logic.command.RegistrationCP;

public enum CommandEnumGuest implements IEnumFactoryEntity {
	
	INSTANCE,
	Home(new HomeCP(), "/index.jsp"),
	Catalog(new CatalogCP(), "/view3images.jsp"),
	Login(new LoginCP(), "/login.jsp"),
	Registration(new RegistrationCP(), "/registration.jsp"),
	Contact(new ContactCP(), "/contact.jsp"),
	About(new AboutCP(), "/about.jsp"),
	ErrorPage404(new ErrorPage404CP(), "/404.jsp");
	
	private ICommandProcessor command;
	private String view;
	
	CommandEnumGuest(ICommandProcessor command, String view){
		this.command = command;
		this.view = view;
	}
	
	CommandEnumGuest() {
	}

	public ICommandProcessor getCommand() {
		return command;
	}
	
	public String getView() {
		return view;
	}

	@Override
	public ICommandProcessor getMyCommand(String name) {
		
		return this.valueOf(name).getCommand();
		
	}

	@Override
	public String getMyView(String name) {
		
		return this.valueOf(name).getView();
	}
}
