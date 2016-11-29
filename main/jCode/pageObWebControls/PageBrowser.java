package main.jCode.pageObWebControls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class PageBrowser 
{
	static WebDriver driver;
	Map<String, WebElement> useFullElement = new HashMap<String, WebElement>();
	
	public PageBrowser(String browserType)
	{switch(browserType)
		{
			/*one might want to make that configurable*/
		case "FF": 	System.setProperty("webdriver.gecko.driver","C:/apps/javadeps/geckodriver.exe");
					driver=new FirefoxDriver();
		break;
		case "IE":  driver = new InternetExplorerDriver();
		break;
		default:	driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void toFPage(String url)
	{
		driver.navigate().to(url);
		//get the pagenumber here to check what page should be returned
		//return the Page with its identifier to check correct page. 
	}

	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static WebElement findLazyElement(By by, int secondsToWait)
	{
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		return wait.until(((WebDriver d)->d.findElement(by)));
	}
	
	public void findAllClickableElements()
	{
		//find everything with href and onclick
		List<WebElement> clickables;
		while(!driver.findElement(By.tagName("body")).isDisplayed());
				{
		clickables = driver.findElements(By.xpath("//*[@href]"));
		clickables.addAll(driver.findElements(By.xpath("//*[@onclick]")));
				}
		for(WebElement element:clickables)
			
		{
			if(!(element.getAttribute("type")=="hidden"))
			{
				int i =0;
				String text = element.getText();
				Flog.writeLLine(text);
				if(text == "")
				{
					text = element.findElement(By.xpath(".//")).getText();
					if(text == "")
					{
						text = "unknown element" + i;
					}
				}
		
				useFullElement.put(text, element);
				Flog.writeLLine("added element: "+ text);
				i++;
			}
		}
	}
	public void findAllInputFields()
	{
		List<WebElement> fillables;
		while(!driver.findElement(By.tagName("body")).isDisplayed());
		fillables = driver.findElements(By.tagName("input"));
		for(WebElement element:fillables)
		{
			if(!(element.getAttribute("type")=="hidden"))
			
			{
				int i =0;
				String text = element.getText();
				Flog.writeLLine(text);
				if(text == "")
				{
					text = element.findElement(By.xpath(".//")).getText();
					if(text == "")
					{
						text = "unknown element" + i;
					}
				}
				
				
				useFullElement.put(text, element);
				Flog.writeLLine("added element: "+ text);
				i++;
			}
		}
	}
	
	public Map<String, WebElement> getUseFulElements()
	{
		return useFullElement;
	}
}
