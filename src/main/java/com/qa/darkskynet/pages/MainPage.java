package com.qa.darkskynet.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.darkskynet.Base.BasePage;
import com.qa.darkskynet.util.ElementUtil;
import com.qa.darkskynet.util.JavaScriptUtil;

public class MainPage extends BasePage {

	WebDriver driver;
	JavaScriptUtil javaScriptUtil;
	ElementUtil elementUtil;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	    By today = By.xpath("//span[contains(text(),'Today')]");
	    By lowestTempr = By.xpath("//span[ @class='minTemp']");
		By highestTempr = By.xpath("//span[@class='maxTemp']");
		By tempListInspect = By.xpath("(//div[@class='temps'] )[2] //span//span");
		
     public  ArrayList<Integer> getTempList() {
    	 
    	WebElement element=driver.findElement(today);
 		javaScriptUtil.scrollIntoView(element, driver);
 		elementUtil.waitForElementVisible(today);
 		elementUtil.doClick(today);
 
 List<WebElement> tempList = driver.findElements(tempListInspect);
		ArrayList<Integer> TempInt = new ArrayList<Integer>();

		for (int i = 0; i < tempList.size(); i++) {
			String tempList1 = tempList.get(i).getText();
			//String tempList1 = tempList.get(i).getText().substring(0, 2);
			String TempString = tempList1.replaceAll("[^0-9]", "");

			TempInt.add(Integer.parseInt(TempString));
		}

		return TempInt;
}


public int ExcpectedMaxTemp() {
	
	ArrayList<Integer> tempIntMax=getTempList();
	//System.out.println("Highest temprature is:::::");
   return Collections.max(tempIntMax);

}

public int ExpectedMinTemp() {
	ArrayList<Integer> tempIntMin=getTempList();
	//System.out.println(" Expected Lowest temprature is:::::");
    return Collections.min(tempIntMin);
	
}
 
public int ActualMintemp() {
	
	 String text= elementUtil.doGetText(lowestTempr);
	String TextString = text.replaceAll("[^0-9]", "");
	int actualMintemp=Integer.parseInt(TextString);
	return actualMintemp;
}




//public boolean ActualMintemp() {
//	
//	 String text= elementUtil.doGetText(lowestTempr);
//	String TextString = text.replaceAll("[^0-9]", "");
//	int actualMintemp=Integer.parseInt(TextString);
//
//	if (actualMintemp==minTemp()) {
//	System.out.println("Actual Lowest temprature is:::::");	
//		return true;
//	}
//	return false;
//}

public int ActualMaxtemp() {
	
	 String text= elementUtil.doGetText(highestTempr);
	String TextString = text.replaceAll("[^0-9]", "");
	int actualMaxtemp=Integer.parseInt(TextString);
	return actualMaxtemp;
}

//public boolean  ActualMaxtemp() {
//	
//	 String text= elementUtil.doGetText(highestTempr);
//	String TextString = text.replaceAll("[^0-9]", "");
//	int actualMaxtemp=Integer.parseInt(TextString);
//	System.out.println("Actual max temrature is::"+actualMaxtemp);
//	if (actualMaxtemp==maxTemp()) {
//		return true;
//	}
//	return false;
//}

}






