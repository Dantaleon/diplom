package logic.abstraction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.NextPage;

public interface ICommandProcessor {
	
	NextPage execute (HttpServletRequest request, HttpServletResponse response, NextPage nextPage);
}
