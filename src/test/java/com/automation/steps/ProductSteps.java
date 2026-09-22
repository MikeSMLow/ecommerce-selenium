package com.automation.steps;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.automation.component.ProductCard;
import com.automation.core.DriverFactory;
import com.automation.models.Product;
import com.automation.pages.AddedProductModal;
import com.automation.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
	
	private final HomePage homePage = new HomePage();
	
	@Given("the user navigates to the home page")
	public void userNavigatesToHomePage() {
		//Direct browser navigation
		DriverFactory.getDriver().get("https://automationexercise.com");
		Assertions.assertTrue(homePage.isPageLoaded(), "Home page failed to load.");
	}
	
	@When("the user adds {string} to the cart")
	public void userAddsProductToCart(String productName) {
		// Leverages the dynamic XPath component selector
		ProductCard card = homePage.getProductByName(productName);
		card.clickAddToCart();
	}
	
	@Then("the product should be added successfully")
	public void verifyProductAddedSuccessfully() {
		AddedProductModal modal = new AddedProductModal();
		Assertions.assertTrue(modal.isConfirmationModalVisible(), "Confirmation modal failed to load.");
	}
	
	@Then("the product card for {string} should display the following details:")
    public void verifyProductCardDetails(String productName, List<Product> expectedProducts) {
        // Cucumber automatically converts the DataTable into a List<Product> using Jackson
        Product expected = expectedProducts.get(0);
        ProductCard actualCard = homePage.getProductByName(productName);

        Assertions.assertEquals(expected.getName(), actualCard.getProductName(), "Product name mismatch.");
        
        // Remove the "Rs." abbreviation completely, then trim any lingering whitespace
        String uiPriceText = actualCard.getProductPrice();
        String rawPrice = uiPriceText.replace("Rs.", "").trim();
        
        BigDecimal actualPrice = new BigDecimal(rawPrice);

        Assertions.assertTrue(
        	    expected.getPrice().compareTo(actualPrice) == 0, 
        	    "Product price mismatch. Expected: " + expected.getPrice() + " but was: " + actualPrice
        	);
    }

}
