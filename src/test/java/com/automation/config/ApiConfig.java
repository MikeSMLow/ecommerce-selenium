package com.automation.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class ApiConfig {
	public static RequestSpecification getBaseSpec() {
		return new RequestSpecBuilder()
				.setBaseUri("https://automationexercise.com")
				.setContentType(ContentType.URLENC) // Crucial for AE's form-data APIs
				.addHeader("User-Agent", "PostmanRuntime/7.32.3") // Bypass Cloudflare
				.build();
	}
}
