package main.jCode.pageObjects;

import main.jCode.pageElements.PageTypes;

public interface IPage {

	public IPage initiate();
	public PageTypes getPageType();
	public IPage expandMenu(String key);
	public IPage openMainMenuItem(String key);
	
}
