package com.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class DriverFactory {
	
	// ThreadLocal for isolated WebDriver instance per thread
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	// Private constructor prevents instantiation of this utility class
	private DriverFactory() {
	}
	
	// Retrieves the isolated WebDriver instance for the current thread.
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	//Initializes the driver if it hasn't been instantiated for this thread.
	public static void initDriver(String browser) {
		if (driver.get() == null) {
			WebDriver webDriver;
			
			// Determine headless mode from GitHub Actions CI environment or local Maven flag
			boolean isHeadless = Boolean.parseBoolean(System.getenv("CI")) || Boolean.parseBoolean(System.getProperty("headless"));
			
			switch (browser.toLowerCase()) {
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (isHeadless) {
					firefoxOptions.addArguments("--headless");
				}
				webDriver = new FirefoxDriver(firefoxOptions);
				break;
			case "chrome":
			default:
				ChromeOptions chromeOptions = new ChromeOptions();
				if (isHeadless) {
					chromeOptions.addArguments("--headless=new");
					chromeOptions.addArguments("--window-size=1920,1080"); 
					chromeOptions.addArguments("--disable-gpu");
				}
				webDriver = new ChromeDriver(chromeOptions);
				break;
			}
			
			if (!isHeadless) {
			    webDriver.manage().window().maximize();
			}
			driver.set(webDriver);
		}
	}
	
	//Quits the driver and cleans up the ThreadLocal reference to prevent memory leaks.
	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove(); // Crucial: removes the thread's value after execution
		}
	}

}
