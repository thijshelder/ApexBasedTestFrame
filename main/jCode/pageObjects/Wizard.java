package main.jCode.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Wizard extends MainPage implements IPage{
	
	WebElement FIFrame;

	public Wizard(WebDriver driver) throws InterruptedException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public IPage initiate()
	{
		
		
		return this;
	}

}
