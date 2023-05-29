package com.logic.abstraction;

public interface IEnumFactoryEntityAJAX {
	
	public ICommandProcessorAJAX getMyCommand(String name);
	
	public String getMyView(String name);
}
