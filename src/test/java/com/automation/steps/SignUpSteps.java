package com.automation.steps;

import com.automation.core.DriverFactory;
import com.automation.pages.LoginPage;
import com.automation.pages.SignUpDetailsPage;
import com.automation.models.AccountDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;

public class SignUpSteps {

	private final LoginPage loginPage = new LoginPage();
	private final SignUpDetailsPage signUpDetailsPage = new SignUpDetailsPage();
	
	@Given("the user is on the login and signup page")
	public void navigateToLoginPage() {
		// Navigate directly to the login route using the isolated thread driver
		DriverFactory.getDriver().get("https://automationexercise.com/login");
		
		Assertions.assertTrue(loginPage.isPageLoaded(), "The Login/Signup page did not load correctly.");
	}
	
	@When("the user signs up with name {string} and email {string}")
	public void submitSignUpForm(String name, String email) {
		// Cucumber automatically passes the strings from the feature file into these parameters
        loginPage.signUp(name, email);
	}
	
	@Then("the user should be directed to the account details page")
	public void verifySignUpDetailsPageLoad() {
		Assertions.assertTrue(signUpDetailsPage.isPageLoaded(), "Account details form is not visible.");
	}
	
	@When("the user fills the account details with the following data:")
	public void fillRegistrationForm(AccountDetails user) {
		// Cucumber automatically intercepts the Gherkin DataTable, runs it through 
        // your DataTableRegistry, and injects the fully populated POJO right here.
        signUpDetailsPage.fillAccountDetails(user);
	}
	
	@When("the user clicks the create account button")
	public void submitRegistration() {
		signUpDetailsPage.clickCreateAccount();
	}
	
	@Then("a successful account creation message should be displayed")
	public void verifyAccountCreated() {
		Assertions.assertTrue(signUpDetailsPage.isAccountCreated(), "Account Created confirmation was not displayed.");
	}
}
