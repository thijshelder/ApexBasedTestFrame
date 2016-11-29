package main.jCode.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.jCode.pageObWebControls.*;

public class Login {
	WebElement UsernameField_login;
	WebElement PasswordField_login;
	WebElement LoginButton_login;
	WebDriver driver;
	
	public Login(WebDriver driver)
	{
		this.driver = driver;
		UsernameField_login = PageBrowser.findLazyElement(By.name("p_t01"),10);
		PasswordField_login = PageBrowser.findLazyElement(By.name("p_t02"),10);
		LoginButton_login = PageBrowser.findLazyElement(By.xpath("//button[@type='button']"),10);
	}
	
	public MainPage doLogin(String username, String passWord) throws InterruptedException
	{
		UsernameField_login.sendKeys(username);
		PasswordField_login.sendKeys(passWord);
		LoginButton_login.click();
		return new MainPage(driver);
	}

	
}
