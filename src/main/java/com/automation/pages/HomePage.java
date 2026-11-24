package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.components.ProductCard;
import com.automation.core.BasePage;

public class HomePage extends BasePage {

	// 1. Locators
	private final By homeSlider = By.cssSelector("section#slider");
	private final By productCards = By.className("product-image-wrapper");
	private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
	
	// 2. Action Methods
	
	public boolean isPageLoaded() {
		try {
			// Reuse explicit wait from BasePage
			waitForVisibility(homeSlider);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ProductCard getProductByName(String productName) {
		// 1. Construct an XPath that filters at the DOM level.
        // It finds the root container (e.g., .single-products) WHERE the child <p> equals the name.
        // Using normalize-space() prevents false negatives caused by trailing spaces in the HTML.
        String xpathFormatter = "//div[contains(@class, 'product-image-wrapper')][.//p[normalize-space(text())='%s']]";
        String exactXPath = String.format(xpathFormatter, productName);
       
        // 2. Create the dynamic By locator
        By dynamicCardLocator = By.xpath(exactXPath);
       
        
        // 3. Return a new instance of your component using this precise locator
        return new ProductCard(dynamicCardLocator);
	}
	
	public boolean isLoggedInAs(String username) {
		String xpathFormatter = "//li//a[contains(text(), 'Logged in as')]//b[text()='%s']";
		String exactXPath = String.format(xpathFormatter, username);
		By loggedInUserText = By.xpath(exactXPath);
		
		try {
            waitForVisibility(loggedInUserText);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	
	public void clickDeleteAccount() {
        click(deleteAccountLink);
    }

}
