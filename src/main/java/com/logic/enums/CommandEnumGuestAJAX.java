package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.ErrorPage404CP;

public enum CommandEnumGuestAJAX implements IEnumFactoryEntity {
	
	INSTANCE;

	@Override
	public ICommandProcessor getMyCommand(String command) {
		
		return new ErrorPage404CP();
		
	}

	@Override
	public String getMyView(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
