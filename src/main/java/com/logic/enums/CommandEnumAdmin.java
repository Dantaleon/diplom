package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.ErrorPage404CP;

public enum CommandEnumAdmin implements IEnumFactoryEntity {
	INSTANCE,
	red(1),
	green(2),
	blue(3);
	
	private int number;
	private CommandEnumAdmin self;
	
	CommandEnumAdmin(int i) {
		this.number = i;
	}

	CommandEnumAdmin() {
		this.self = this;
	}
	
	public int getIntColor() {
		return number;
	}
	
	public CommandEnumAdmin getSelfReference() {
		return self;
	}

	@Override
	public ICommandProcessor getMyCommand(String command) {
		
		return new ErrorPage404CP();
		
	}

	@Override
	public String getMyView(String name) {
		
		return null;
	}
	
	
	
}
