package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.component.ProductCard;

public class HomePage extends BasePage {

	// 1. Locators
	private final By homeSlider = By.cssSelector("section#slider");
	private final By productCards = By.className("product-image-wrapper");
	
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
	
	
}
