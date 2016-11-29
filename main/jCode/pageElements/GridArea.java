package main.jCode.pageElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.jCode.pageObWebControls.*;

public class GridArea 
{
	
	WebDriver driver;
	/*This ArrayList of arraylist of entries, consisting of an arraylist of items with a label
	 * 
	 */
	ArrayList<ArrayList<ArrayList<String>>> entries = new ArrayList<ArrayList<ArrayList<String>>>();
	
	public GridArea(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public Map<String, WebElement> initiateGrid(WebElement gridContainer)
	{
	Map<String, WebElement> gridRows = new HashMap<String, WebElement>();
	ArrayList<String> headers = new ArrayList<String>();
	String id_gridContainer = gridContainer.getAttribute("id");
	String id_table = gridContainer.findElement(By.id(id_gridContainer+"_worksheet_id")).getAttribute("value");
	List<WebElement> tables = driver.findElements(By.id(id_table));
	if(tables.size()>0)
	{	
		List<WebElement> rows = tables.get(0).findElements(By.tagName("tr"));
		Flog.writeLLine(((Integer)rows.size()).toString());
		for(int i=1;i<tables.size();i++)
			
			{	
				if (rows.size()==1)
					{
						rows=tables.get(i).findElements(By.tagName("tr"));
					}
		
			}
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
				else
					{
						String header = element.getAttribute("id");
						headers.add(header);
					}
			Flog.writeLLine("Headers duly found");
			}
		Flog.writeLLine("now finding table data cells...." );
		for(WebElement element1:rows)
			{	//elements of a row are cells, of which I would like to know the value.
				List<WebElement> cells = element1.findElements(By.tagName("td"));
		//first row does only contain headers. 
				cells.addAll(element1.findElements(By.tagName("th")));
				if(cells.isEmpty())
					{
					Flog.writeLLine("nt");
					}
		Flog.writeLLine(((Integer)cells.size()).toString());
		int i = 0;
		//debugger
		ArrayList<ArrayList<String>> tussen = new ArrayList<ArrayList<String>>();
			for(WebElement element2:cells)
				{	
					ArrayList<String> cellList = new ArrayList<String>();
					Flog.writeLLine(headers.get(i)+ " " +element2.getAttribute("headers")+ "................."+element2.getText());
					//provides a label for the cell
					cellList.add(element2.getAttribute("headers"));
					//and the actual cellValue
					cellList.add(element2.getAttribute(element2.getText()));
					gridRows.put(element2.getText(), element1);
					i++;
					tussen.add(cellList);
				}
			entries.add(tussen);	
			}
		}
return gridRows;
}
	
	
	public ArrayList<ArrayList<ArrayList<String>>> getEntries()
	{
		return entries;
	}
}