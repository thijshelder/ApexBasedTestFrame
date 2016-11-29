package main.jCode.pageObjects;

import main.jCode.pageElements.*;
import main.jCode.pageObWebControls.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends MainPage implements IPage

{
	WebElement searchField;
	WebElement goButton;
	WebElement resetButton;
	WebDriver driver;
	WebElement resultTable;
    Map<String, WebElement> gridRows = new  HashMap<String, WebElement>();
    ArrayList<ArrayList<ArrayList<String>>> results =null;  

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		
	}
	
	public IPage initiate()
	{
		searchField= driver.findElement(By.id("P1000_REPORT_SEARCH"));
		goButton = driver.findElement(By.xpath(".//button[contains(@onclick,'GO')]"));
		resetButton = driver.findElement(By.xpath(".//button[contains(@onclick,'RESET')]"));
		return this;
	}
	
	public IPage searchForItem(String term)
	{
		searchField.sendKeys(term);
		goButton.click();
		try
		{
			Thread.sleep(2200);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		resultTable = driver.findElement(By.className("t-IRR-region"));
		try{Thread.sleep(2222);}catch(InterruptedException e){e.printStackTrace();}

		GridArea area = new GridArea(driver);
		gridRows = area.initiateGrid(resultTable);
		results = area.getEntries();
		/*
		String id_gridContainer = resultTable.getAttribute("id");
		//debug
		String id_table = resultTable.findElement(By.id(id_gridContainer+"_worksheet_id")).getAttribute("value");
		Flog.writeLLine(id_gridContainer);
		Flog.writeLLine(id_table);
		List<WebElement> tables = resultTable.findElements(By.id(id_table));
		//debuggingrows
		try
		{
			Thread.sleep(2200);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		List<WebElement> rows = tables.get(1).findElements(By.tagName("tr"));
		Flog.writeLLine(((Integer)rows.size()).toString());
		Flog.writeLLine(rows.get(0).getAttribute("id")+rows.get(0).getText());
		List<WebElement> headerCells = rows.get(0).findElements(By.tagName("th"));
		for(WebElement element:headerCells)
			{
				List<WebElement> refs = element.findElements(By.tagName("a"));
				if(!refs.isEmpty())
				{
					String header  = refs.get(0).getAttribute("data-column");
					String header2 = refs.get(0).getAttribute("class");
					String header3 = refs.get(0).getAttribute("href");
					headers.add(header);
					Flog.writeLLine(header + " " + header2 + " "+ header3);
				}
			}
		try
		{
			Thread.sleep(2200);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		for(WebElement element1:rows)
		{
			//elements of a row: cells.
			//VEEEEEEERY Slow. tries to find either type of element and will wait for either the whole implicitwaitperiod.
			List<WebElement> cells = element1.findElements(By.xpath(".//td"));
			cells.addAll(element1.findElements(By.xpath(".//th")));
			Flog.writeLLine(((Integer)cells.size()).toString());
			String rowID_basedOnRelationId = cells.get(0).findElement(By.tagName("a")).getText();
			Flog.writeLLine(rowID_basedOnRelationId);
			gridRows.put(rowID_basedOnRelationId, element1);
				
			}*/
		return this;
	}
	
	public ClientPage click_ClientRow(String id)
	{
		WebElement details 				= gridRows.get(id);
		List<WebElement> cells2 		= details.findElements(By.className("u-tL"));
		cells2.addAll(details.findElements(By.className("u-tR")));
		Flog.writeLLine(((Integer)cells2.size()).toString());
		
		for(int i = 0;i<cells2.size();i++)
		{
			Flog.writeLLine(cells2.get(i).getText());
			
			if(cells2.get(i).getText().equals(id))
			{
				cells2.get(i).findElement(By.tagName("a")).click();
			}
		}
		
		return new ClientPage(driver);
	}
	//yup, that is a table - like structure. or a cube. Or something multidimensional that would make Euclid weep. 
	public ArrayList<ArrayList<ArrayList<String>>> getEntries()
	{
		return results;
	}
}
