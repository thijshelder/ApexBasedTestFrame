package main.jCode.pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.jCode.pageElements.PageTypes;
import main.jCode.pageObWebControls.*;

public class MainPage implements IPage
{	
	String pageId;
	PageTypes type;
	String name = null;
	Map<String, WebElement> menuItems = new HashMap<String, WebElement>();
	WebDriver driver;
	
	public MainPage(WebDriver driver) 
		{
			type = PageTypes.MAIN;
			pageId = null;
			this.driver = driver;
		}
		 
	public IPage initiate()
	{
		try{
			Thread.sleep(2200);
			}
		catch(InterruptedException e)
		{e.printStackTrace();}
		WebElement lhMenu = driver.findElement(By.id("t_TreeNav"));
		List<WebElement> items = lhMenu.findElements(By.tagName("li"));
		for(WebElement element:items)
			{
				String name = (element.findElement(By.tagName("a")).getText());
				menuItems.put(name, element);
				Flog.writeLLine("Element found " + name);		
			}
		return this;
	}

	public PageTypes getPageType()
	{
		return type;
	}
	
	public IPage expandMenu(String key)
	{
		try{
			Thread.sleep(2200);
			}
		catch(InterruptedException e)
		{e.printStackTrace();}
		List<WebElement> items = menuItems.get(key).findElements(By.xpath(".//span[@class='a-TreeView-toggle']"));
		if (items.size()==1)
		{
			
			menuItems.put(key+"_expander", items.get(0));
			Flog.writeLLine("expander added to " + key);
			items.get(0).click();
			
		}
		initiate();
		return this;
	}
	public IPage openMainMenuItem(String key, PageTypes type)
	{
		menuItems.get(key).click();
		switch(type)
		{case BUTTONS: return new ButtonPage(driver);
		
		case GRID: return new GridPage(driver);
		case IFRAME: return null; /*to be implemented*/
		case SEARCH:return new SearchPage(driver); 
		case WIZARD:return null;
		case MAIN:return null;
		default:
		return null;
		}
		
	}

	@Override
	public IPage openMainMenuItem(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getPageNumber()
	{
		return pageId;
	}
}
