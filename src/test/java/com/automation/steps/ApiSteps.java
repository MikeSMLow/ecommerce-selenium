package com.automation.steps;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.automation.config.ApiConfig;
import com.automation.models.AccountDetails;
import com.automation.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiSteps {

	private Response apiResponse;
	private AccountDetails apiUser;
	
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
	
	@Given("the user data is prepared for {string}")
	public void prepareUserData(String email) {
		// Prepare the exact same POJO used in the UI tests
		apiUser = AccountDetails.builder()
				.name("Miguel Sanchez")
				.title("Mr.")
				.firstName("Miguel")
				.lastName("Sanchez")
				.email(email)
				.password("SecureApiPass123!")
				.build();
	}
	
	@When("a POST request is made to create the account")
	public void createAccountViaApi() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> formParams = mapper.convertValue(
				apiUser,
				new TypeReference<Map<String, Object>>() {}
		);
		
		apiResponse = given()
				.spec(ApiConfig.getBaseSpec())
				.formParams(formParams)
				.post("/api/createAccount");
	}
	
	@Then("the API should return a {string} success message")
	public void verifySuccessMessage(String expectedMessage) {
		JsonPath jsonPath = new JsonPath(apiResponse.asString());
		
		// Parse the JSON tree manually
		String actualMessage = jsonPath.getString("message");
		
		Assertions.assertEquals(expectedMessage, actualMessage,
				"The API success message did not match.");
	}
	
	@When("a GET request is made to fetch the account details for {string}")
	public void fetchAccountDetails(String email) {
		apiResponse = given()
					.spec(ApiConfig.getBaseSpec())
					.queryParam("email", email)
					.get("/api/getUserDetailByEmail");
	}
	
	@Then("the reponse should contain the user's account correct name and email")
	public void verifyAccountDetails() {
		JsonPath jsonPath = new JsonPath(apiResponse.asString());
		
		Assertions.assertEquals(200, jsonPath.getInt("responseCode"));
		Assertions.assertEquals(apiUser.getFirstName(), jsonPath.getString("user.first_name"));
		Assertions.assertEquals(apiUser.getEmail(), jsonPath.getString("user.email"));
	}
	
	@When("a DELETE request is made using the account email and password")
	public void deleteAccountViaApi() {
		apiResponse = given()
					.spec(ApiConfig.getBaseSpec())
					.formParam("email", apiUser.getEmail())
					.formParam("password", apiUser.getPassword())
					.delete("/api/deleteAccount");
	}
}
