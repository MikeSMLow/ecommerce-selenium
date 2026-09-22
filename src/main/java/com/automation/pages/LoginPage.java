package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.core.BasePage;
import com.automation.pages.SignUpDetailsPage;

public class LoginPage extends BasePage {
	
	private final By loginTitle = By.xpath("//h2[text()='Login to your account']");
	
	private final By signUpNameInput = By.cssSelector("[data-qa='signup-name']");
	private final By signUpEmailInput = By.cssSelector("[data-qa='signup-email']");
	private final By signUpButton = By.cssSelector("[data-qa='signup-button']");
	
	public boolean isPageLoaded() {
		try {
			// Reuse explicit wait from BasePage
			waitForVisibility(loginTitle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public SignUpDetailsPage signUp(String name, String email) {
		type(signUpNameInput, name);
		type(signUpEmailInput, email);
		click(signUpButton);
		return new SignUpDetailsPage();
	}
	

}
