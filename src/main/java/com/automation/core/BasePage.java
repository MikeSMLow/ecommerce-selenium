package com.automation.core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import com.automation.core.DriverFactory;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage() {
		// Retrieve the thread-safe driver for this specific test instance
		this.driver = DriverFactory.getDriver();
		// Initialize the explicit wait with a standard 15-second timeout
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Waits for an element to be present in the DOM and visible on screen (mostly used internally).
	protected WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	// Waits for an element to be visible and enabled such that you can click it (mostly used internally).
	protected WebElement waitForClickability(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	// Reusable helper to scroll elements into the viewport.
	protected void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
	
	// Wrapper for click() that guarantees the element is clickable first.
	protected void click(By locator) {
		WebElement element = waitForClickability(locator);
		scrollIntoView(element);
		element.click();
	}
	
	// Wrapper for sendKeys() that ensures visibility, clears the field, and types.
	protected void type(By locator, String text) {
		WebElement element = waitForVisibility(locator);
		scrollIntoView(element);
		element.clear(); // Prevents appending to existing text (e.g., pre-filled forms)
		element.sendKeys(text);
	}
	
	// Wrapper to safely extract text from an element.
	protected String getText(By locator) {
		WebElement element = waitForVisibility(locator);
		return element.getText().replaceAll("\\s+", " ").trim();
	}
	
	protected void hoverOver(By locator) {
		WebElement element = waitForVisibility(locator);
		scrollIntoView(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	protected void selectByVisibleText(By locator, String text) {
		waitForVisibility(locator);
		Select dropdown = new Select(DriverFactory.getDriver().findElement(locator));
		dropdown.selectByVisibleText(text);
	}

}
