package main.jCode.pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.jCode.pageElements.ButtonArea;
/**Klantpagina differs from other pages as it combines buttons and grids, and some other elements too. 
 * 
 * @author thijs
 *
 *
 */
public class ClientPage extends MainPage implements IPage

{
	WebElement buttonContainer;
	WebElement clientContainer;
	WebDriver driver;
	Map<String, WebElement> buttons = new HashMap<String, WebElement>();
	WebElement backButton;
	
	public ClientPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	
	public IPage initiate()
	{
		//the upper part of the page where info on the particular client is stored 
		//wait a little for WD...<sigh>
		try
		{
			Thread.sleep(2200);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		List<WebElement> areas = driver.findElements(By.xpath(".//div[@role='group']"));
		//Just hope that this id is stable. 
		//nope. it is not. 
		//clientContainer = driver.findElement(By.id("R282403004333712008"));
		clientContainer = areas.get(0);
		//debugging 
		backButton = clientContainer.findElement(By.xpath(".//button[@title='Back']"));
		//and this won't work either
		//buttonContainer = driver.findElement(By.id("R282371561691711906"));
		buttonContainer = areas.get(4);
		ButtonArea area = new ButtonArea();
		buttons = area.initiateButtonArea(buttonContainer);
		return this;
	}
	
	public void goBack()
	{
		backButton.click();
	}
	
	public FramePage clickButton(String buttonName)
	{
		buttons.get(buttonName).click();
		return new FramePage(driver);
	}
	
	
	

}
