package com.logic.abstraction;

public interface IEnumFactoryEntity {
	public ICommandProcessor getMyCommand(String name);
	public String getMyView(String name);
}
