package com.qa.darkskynet.util;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.darkskynet.Base.BasePage;
import com.qa.darkskynet.util.AppConstants;
import com.qa.darkskynet.util.JavaScriptUtil;

public class ElementUtil extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil javaScriptUtil;
	
	

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME);
		javaScriptUtil=new JavaScriptUtil(driver);
	}

	/**
	 * get Title
	 * 
	 * @return
	 */
	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception  occured while getting the title");
		}
		return null;
	}

	/**
	 * to Get Element
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {

		WebElement element = null;
		try {
        element = driver.findElement(locator);
        if(highlightElements)
        	javaScriptUtil.flash(element);
		} catch (Exception e) {
			System.out.println("some exception  occured while getting the element");
		}
		return element;

	}

	/**
	 * wait For Element
	 * 
	 * @param locator
	 */
	public void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,AppConstants.DEFAULT_TIME);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * Visibility Element
	 * 
	 * @param locator
	 * @return
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	/**
	 * Title Wait Method
	 * 
	 * @param title
	 * @return
	 */
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}

	/**
	 * to Do Click
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception  occured while clicking the element");
		}
	}

	/**
	 * to Send Keys
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception  occured while sending  the value");
		}
	}

	/**
	 * Is Displayed
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception  occured while isDisplayed");
		}
		return false;
	}

	/**
	 * Is Selected
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsSelected(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception  occured while isSelected");
		}
		return false;
	}

	/**
	 * Is Enabled
	 * 
	 * @param locator
	 * @return
	 */
	public boolean diIsEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("some exception  occured while isEnabled");
		}
		return false;
	}

	/**
	 * to GetText
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception  occured while getting text ");
		}
		return null;
	}
	
	public  void selectSingleValue(WebDriver driver, By locator, String value) {
		List<WebElement> dropdown = driver.findElements(locator);
		for (int i = 0; i < dropdown.size(); i++) {
			String text = dropdown.get(i).getText();
			// System.out.println(text);

			try {
				if (!text.isEmpty()) {
					if (text.equals(value)) {
						dropdown.get(i).click();
						//System.out.println("correct text:::" + text);
						break;
					}
				}
			} catch (Exception e) {

			}

		}
	}
	
	public  void selectDropDownByVisibleText(WebDriver driver, By locator, String value) {
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(value);

	}
	
	
}














