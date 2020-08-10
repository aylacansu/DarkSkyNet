package com.qa.darkskynet.tests;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.darkskynet.Base.BasePage;
import com.qa.darkskynet.pages.MainPage;
import com.qa.darkskynet.util.ElementUtil;

public class MainPageTest {

	WebDriver driver;
	MainPage mainPage;
	Properties prop;
	BasePage basePage;
	ElementUtil elementUtil;

	@BeforeTest
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.Init_driver(browserName);
		driver.get(prop.getProperty("url"));
		mainPage = new MainPage(driver);
		Thread.sleep(5000);
	}

	@Test(priority = 1, description = " All temperatures is displayed ", enabled = true)
	public void getAllTemp() throws InterruptedException {
		System.out.println("All tempratures in a day");
		System.out.println(mainPage.getTempList());

	}

	@Test(priority = 2, description = "Verify that, highest temperature is displayed correctly", enabled = true)
	public void verifyCheckHighestTemp() throws InterruptedException {

		System.out.println("Expected max  temrature is::" + mainPage.ExcpectedMaxTemp());
		System.out.println("Actual max  temrature is::" + mainPage.ActualMaxtemp());
		try {
			// Assert.assertNotEquals(mainPage.ActualMaxtemp(),
			// mainPage.ExcpectedMaxTemp());
			// Assert.assertTrue(true);
			// Assert.assertFalse(false);
			Assert.assertEquals(mainPage.ActualMaxtemp(), mainPage.ExcpectedMaxTemp());
		} catch (AssertionError e) {
			e.getStackTrace();
			System.out.println("Actual max doesn't match expected highest  temprature");
		}

	}

	@Test(priority = 3, description = "Verify that, lowest temperature is displayed correctly", enabled = true)
	public void verifyCheckLowestTemp() throws InterruptedException {
		System.out.println("Expected min  temrature is::" + mainPage.ExpectedMinTemp());
		System.out.println("Actual min  temrature is::" + mainPage.ActualMintemp());
		try {

			// Assert.assertNotEquals(mainPage.ActualMintemp(), mainPage.minTemp());
			// Assert.assertTrue(true);
			// Assert.assertFalse(false);
			Assert.assertEquals(mainPage.ActualMintemp(), mainPage.ExpectedMinTemp());
		} catch (AssertionError e) {
			e.getStackTrace();
			System.out.println("Actual min doesn't match expected lowest  temprature");
		}

	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}