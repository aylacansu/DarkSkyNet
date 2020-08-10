package com.qa.darkskynet.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
    public static boolean  highlightElements;
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public OptionsManager optionsManager;

	public WebDriver Init_driver(String browserName) {

		System.out.println("Browser name is " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			highlightElements = prop.getProperty("highlight").equals("yes") ? true : false;
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();

			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			if (prop.getProperty("headless").equals("yes")) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);

			} else {
				driver = new FirefoxDriver();

			}
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else {
			System.out.println("Browser name " + browserName + " is not found");
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// driver.get("");

		return driver;
	}

	public Properties init_properties() {

		prop = new Properties();

		String path = "./src/main/java/com/qa/darkskynet/config/config.properties";

		try {

			FileInputStream inputStream = new FileInputStream(path);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("some issue happened with config properties...correct the file");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

}


