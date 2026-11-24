package com.automation.pages;

import org.openqa.selenium.By;
import com.automation.core.BasePage;

public class AccountDeletedPage extends BasePage {
	
	private final By accountDeletedTitle = By.cssSelector("[data-qa='account-deleted']");
    private final By continueButton = By.cssSelector("[data-qa='continue-button']");
    
    public boolean isAccountDeleted() {
        try {
            waitForVisibility(accountDeletedTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickContinue() {
        click(continueButton);
    }

}
