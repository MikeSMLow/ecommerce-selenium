package com.automation.component;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.automation.core.BasePage;

public class ProductCard extends BasePage {
	
	private final By rootCard;
	private final By productName;
	private final By productPrice; 
	private final By addToCartButton;
	private final By addToCartOverlayButton;
	private final By viewProductButton;

	
	public ProductCard (By rootCardLocator) {
		this.rootCard = rootCardLocator;
		
		// We use ByChained links to search inside the passed rootCardLocator
		this.productName = new ByChained(this.rootCard, By.cssSelector(".single-products>div>p"));
		this.productPrice = new ByChained(this.rootCard, By.cssSelector(".single-products>div>h2"));
		this.addToCartButton = new ByChained(this.rootCard, By.cssSelector(".productinfo .add-to-cart"));
		this.viewProductButton = new ByChained(this.rootCard, By.cssSelector("a[href^='/product_details']"));
		this.addToCartOverlayButton = new ByChained(this.rootCard, By.cssSelector(".productinfo .add-to-cart"));
	}
	
	public String getProductName() {
        return getText(productName); // Uses BasePage wrapper to read the DOM
    }
    
    public String getProductPrice() {
        return getText(productPrice); 
    }
	
	public void clickAddToCart() {
		click(addToCartButton);
	}
	
	public void clickAddToCartOverlay() {
		hoverOver(rootCard);
		click(addToCartOverlayButton);
	}
	
	public void clickViewProduct() {
		click(viewProductButton);
	}
	
	


}
