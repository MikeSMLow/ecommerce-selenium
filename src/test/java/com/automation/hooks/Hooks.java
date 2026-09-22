package com.automation.hooks;

import com.automation.core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
	
	@Before("not @api")
	public void setup(Scenario scenario) {
		// Here you can dynamically read the browser from a config file or Maven arguments.
        // E.g., mvn clean test -Dbrowser=firefox. We default to chrome if nothing is passed.
		String browser = System.getProperty("browser", "chrome");
		
		// Initialize the Threadlocal driver for this specific scenario
		DriverFactory.initDriver(browser);
	}
	
	@After("not @api")
	public void teardown(Scenario scenario) {
		WebDriver driver = DriverFactory.getDriver();
		
		// Capture a screenshot if the scenario fails and attach it to the Cucumber report
		if (scenario.isFailed() && driver != null) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				// Note: In Cucumber 7, embed() was replaced by attach()
				scenario.attach(screenshot, "image/png", "Failed_screenshot_" + scenario.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Safely quit the browser and clear the ThreadLocal memory for this thread
		DriverFactory.quitDriver();
	}

}
