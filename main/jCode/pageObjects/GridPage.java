package main.jCode.pageObjects;

import main.jCode.pageElements.ElementUnavailableException;
import main.jCode.pageElements.GridArea;
import main.jCode.pageObWebControls.*;
import main.jCode.pageObjects.MainPage;
import main.jCode.pageObjects.IPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GridPage extends MainPage implements IPage 
{
	String name = "Gridpage";
	WebDriver driver; 
	Map<String, WebElement> gridHeaders = new HashMap<String, WebElement>();
	ArrayList<String> headers = new ArrayList<String>();
	Map<String, WebElement> gridRows = new  HashMap<String, WebElement>();
	
	public GridPage(WebDriver driver)
	{		
		super(driver);
		this.driver = driver;
	}
	
	public GridPage(WebDriver driver, String gridName)
	{		
		super(driver);
		name = gridName;
		this.driver = driver;
	}
	
	public IPage initiate() 
	{
		WebElement gridContainer = driver.findElement(By.className("t-IRR-region"));
		GridArea area = new GridArea(driver);
		gridRows= area.initiateGrid(gridContainer);
		return this;
}
	
	public FramePage click_Row(String id) throws ElementUnavailableException, NullPointerException
	{
		WebElement details = gridRows.get(id);
		WebElement clicker = details.findElement(By.tagName("a"));
		List<WebElement> cells = details.findElements(By.className("u-tL"));
		for(int i = 0;i<cells.size();i++)
		{
			Flog.writeLLine(cells.get(i).getText());
		}
		clicker.click();
		return new FramePage(driver);
	}
}
