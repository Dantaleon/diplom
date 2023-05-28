package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.AboutCP;
import com.logic.command.CatalogCP;
import com.logic.command.ContactCP;
import com.logic.command.ErrorPage404CP;
import com.logic.command.HomeCP;
import com.logic.command.LoginCP;
import com.logic.command.RegistrationCP;
import com.logic.commandAJAX.HeaderIncludeCP;

public enum CommandEnumGuest implements IEnumFactoryEntity {
	
	INSTANCE,
	Home(new HomeCP(), "/WEB-INF/jsp/index.jsp"),
	Catalog(new CatalogCP(), "/WEB-INF/jsp/view3images.jsp"),
	Login(new LoginCP(), "/WEB-INF/jsp/login.jsp"),
	Registration(new RegistrationCP(), "/WEB-INF/jsp/registration.jsp"),
	Contact(new ContactCP(), "/WEB-INF/jsp/contact.jsp"),
	About(new AboutCP(), "/WEB-INF/jsp/about.jsp"),
	ErrorPage404(new ErrorPage404CP(), "/WEB-INF/jsp/404.jsp");
	
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
