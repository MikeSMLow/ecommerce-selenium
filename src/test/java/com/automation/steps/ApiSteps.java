package com.automation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import com.automation.models.Product;

import java.util.List;
import java.util.Map;

public class ApiSteps {

	private Response apiResponse;
	
	@When("the user requests the product list from the API")
	public void requestProductList() {
		// Executes a GET request and stores the full response (headers, status, body)
		apiResponse = RestAssured
				.given()
					.baseUri("https://automationexercise.com")
					.header("Content-Type", "application/json")
					// Spoof a real browser to bypass Cloudflare bot protection
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept", "application/json")
				.when()
					.get("/api/productsList");
		
		// This will print the raw server response directly to your console
        apiResponse.prettyPrint();
	}
	
	@Then("the API should return a successful response")
	public void verifySuccessfulResponse() {
		// AutomationExercise API wraps the status inside the JSON body as "responseCode"
		int responseCode = apiResponse.jsonPath().getInt("responseCode");
		Assertions.assertEquals(200, responseCode, "API did not return a 200 OK status.");
	}
	
	@Then("the response should contain a list of products")
	public void verifyProductListIsNotEmpty() {
		// Extracts the "products" array from the JSON root into a Java List
		List<Product> products = apiResponse.jsonPath().getList("products", Product.class);
		
		Assertions.assertNotNull(products, "Product list is null.");
		Assertions.assertFalse(products.isEmpty(), "The API returned an empty product list");
		
		// Print the first product's name to the console to verify extraction
		System.out.println("First product found: " + products.get(0).getName());
		
		// Because your POJO uses BigDecimal, Jackson automatically converted the API price for you!
        System.out.println("First product price: " + products.get(0).getPrice());
	}
}
