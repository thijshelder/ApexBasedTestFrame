package main.jCode.pageElements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.jCode.pageObWebControls.*;

public class ButtonArea {
	
	
	
	public Map<String, WebElement> initiateButtonArea(WebElement area)
	{
		
		Map<String, WebElement> buttons = new HashMap<String, WebElement>();
		List<WebElement> lister =area.findElements(By.tagName("li"));
		Flog.writeLLine("Listitems found " + lister.size() );
		//this wil only work if paused for some time
		try{Thread.sleep(1000);}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		for (WebElement list:lister)
			{
				String buttonId = list.getText();
				if (buttonId == null)
					{buttonId = list.findElement(By.xpath(".//*")).getText();}
					
				    buttons.put(buttonId +"_Button",list.findElement(By.xpath((".//a"))));
					Flog.writeLLine("Buttons Found :" + buttonId+"_Button");
			}
		return buttons;
	}
	
	
	

}
