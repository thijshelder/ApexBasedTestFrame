package main.jCode.pageObjects;

import main.jCode.pageElements.ButtonArea;
import main.jCode.pageObWebControls.*;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonPage extends MainPage
/**Stores a page with buttons. Will store all buttons in a map, where the button label will be used as key. Allows for pressing buttons
 * using the <label>_Button. 
 * 
 */
{
	WebDriver driver; 
	/*identifies a button by its identifier. 
	 * 
	 */
	Map<String, WebElement> buttons = new HashMap<String, WebElement>();
	String name;
	
	
	public ButtonPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
	}

	public ButtonPage(String name, WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		
	}

	public IPage initiate()
	
	{	super.initiate();
	//if we know the element where the buttons are kept
		WebElement bContainer = PageBrowser.findLazyElement(By.className("t-Body-contentInner"),10);

		ButtonArea area = new ButtonArea();
		buttons = area.initiateButtonArea(bContainer);
		return this;
	}
	/*identifies a button by its label.
	 * @param label = label of button plus the suffix _Button (to avoid confusion with menuelements
	 */
	public IPage clickButton(String label)
	{//needs to return whatever pageType. Could be either another Buttonpage or a gridpage or a frame
		String buttonId = label +"_Button";
		buttons.get(buttonId).click();
		return new MainPage(driver);
	}

}
