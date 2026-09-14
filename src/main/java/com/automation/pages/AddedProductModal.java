package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AddedProductModal extends BasePage {
	
	private final By rootModal;
	private final By viewCartButton;
	private final By continueShoppingButton;
	
	public AddedProductModal () {
		this.rootModal = By.className("modal-content");
		
		this.viewCartButton = new ByChained(this.rootModal, By.cssSelector("a[href^='/view_cart']"));
		this.continueShoppingButton = new ByChained(this.rootModal, By.className("close-modal"));
	}
	
	public boolean isConfirmationModalVisible() {
		try {
			// Reuse explicit wait from BasePage
			waitForVisibility(rootModal);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
