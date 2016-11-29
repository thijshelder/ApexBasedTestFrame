package main.jCode.pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.jCode.pageObWebControls.*;
import main.jCode.pageObjects.MainPage;
/** Description of an IFrame within FINE. The class will create a map of any textfield available on the frame.
 * the key of the map will have the same value as the label of the inputfield. 
 * It will also hold the default buttons: save, back, delete.
 * 
 * 
 * @author thijs
 *	
 **/
public class FramePage extends MainPage implements IPage
{
	WebElement FIFrame;
	WebElement FIform;
	WebElement closeButton = null;
	WebElement backButton=null;
	WebElement saveButton = null;
	WebElement trashButton = null;
	List<WebElement> fields;
	Map<String, WebElement> inputMap = new HashMap<String, WebElement>();
	String FiPageId;
	WebDriver driver;
	
	public FramePage(WebDriver driver)
	{
		
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public IPage initiate()
	{
		//close button is outside of frame, for some reason. 
		closeButton = driver.findElement(By.xpath(".//button[@title='Close']"));
		//activate the IFrame itself. 
		driver.switchTo().frame(PageBrowser.getDriver().findElement(By.tagName("iframe")));
		//debug. Must figure out better way to fix timing issue.  
		try 
			{
			Thread.sleep(2000);
			} 
		catch (InterruptedException e) 
			{
			// TODO: handle exception
			}	
		//Iframe has some default buttons 
		FIform = driver.findElement(By.id("wwvFlowForm"));
		FiPageId= driver.findElement(By.id("pFlowStepId")).getAttribute("value");
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		
		for  (WebElement element:buttons)
		{
			if(!(element.getAttribute("title")==null))
			{
			Flog.writeLLine(element.getAttribute("title"));
			String buttonName = element.getAttribute("title");
			while(buttonName == null)
			{
				Flog.writeLLine("waiting");
			}
			switch(buttonName)
			{
			case "Cancel":backButton  = element;
			break;
			case "Apply Changes":saveButton  = element;
			break;
			case "Delete":trashButton= element;
			break;
			default: Flog.writeLLine(buttonName +" is not known in current context");
			}
		}}
		
		//find all other fields
		fields = driver.findElements(By.xpath(".//div[contains(@id,'"+FiPageId+"')]"));
		//debugging
		
		try 
		{
		Thread.sleep(2000);
		} 
	catch (InterruptedException e) 
		{
		// TODO: handle exception
		}
		//find all inputfields, store in a map, add key of type String with the value of their labels. 
		for(WebElement field:fields)
		{
			if(field.isDisplayed())
			{
				List<WebElement> inputFields = field.findElements(By.xpath(".//*[contains(@name,'p_t')]"));
				//long shot -but it worked! 
				inputMap.put(field.findElement(By.tagName("label")).getText(),inputFields.get(0));
				Flog.writeLLine("added :"+field.findElement(By.tagName("label")).getText()+" " + inputFields.get(0).getAttribute("name"));
			}
		}
		return this;
		
		
	}
	
	
	public void closeFrame(MainPage page)
	{
		//must fix; returntype should be more generic. 
		backButton.click();
		driver.switchTo().defaultContent();
		
		try 
		{
			Thread.sleep(1100);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		page.initiate();

	}
	
	public FramePage saveChanges()
	{
		saveButton.click();
		return this;
	}
	
	public FramePage provideInput(String fieldName, String value)
	{
		WebElement field = inputMap.get(fieldName);
		field.sendKeys(value);
		return this;
	}
	
}
