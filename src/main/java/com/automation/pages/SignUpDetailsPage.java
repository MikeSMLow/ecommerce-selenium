package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.core.BasePage;
import com.automation.models.AccountDetails;

public class SignUpDetailsPage extends BasePage {
	
	private final By signUpTitle = By.xpath("//b[text()='Enter Account Information']");
	
	// Account Information Locators
	private final By genderMrRadio = By.id("id_gender1");
	private final By passwordInput = By.id("password");
	private final By daysDropdown = By.id("days");
	private final By monthsDropdown = By.id("months");
	private final By yearsDropdown = By.id("years");
	
	// Address Information Locators
	private final By firstNameInput = By.id("first_name");
	private final By lastNameInput = By.id("last_name");
	private final By addressInput = By.id("address1");
	private final By countryDropdown = By.id("country");
	private final By stateInput = By.id("state");
	private final By cityInput = By.id("city");
	private final By zipcodeInput = By.id("zipcode");
	private final By mobileInput = By.id("mobile_number");
	
	private final By createAccountButton = By.cssSelector("[data-qa='create-account']");
	private final By accountCreatedTitle = By.cssSelector("[data-qa='account-created']");

	public boolean isPageLoaded() {
		try {
			waitForVisibility(signUpTitle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void fillAccountDetails(AccountDetails user) {
		// Select Gender
		if (user.getTitle().equalsIgnoreCase("Mr.")) {
			click(genderMrRadio);
		}
		
		type(passwordInput, user.getPassword());
		
		// Utilizing the new BasePage wrapper
		selectByVisibleText(daysDropdown, user.getDay());
		selectByVisibleText(monthsDropdown, user.getMonth());
		selectByVisibleText(yearsDropdown, user.getYear());
		
		type(firstNameInput, user.getFirstName());
		type(lastNameInput, user.getLastName());
		type(addressInput, user.getAddress());
		
		selectByVisibleText(countryDropdown, user.getCountry());
		
		type(stateInput, user.getState());
		type(cityInput, user.getCity());
		type(zipcodeInput, user.getZipcode());
		type(mobileInput, user.getMobile());
	}
	
	public void clickCreateAccount() {
		click(createAccountButton);
	}
	
	public boolean isAccountCreated() {
		try {
			waitForVisibility(accountCreatedTitle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
