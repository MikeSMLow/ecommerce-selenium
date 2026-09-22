package com.automation.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	
	private String name;
	private int quantity;
	
	@JsonDeserialize(using = PriceDeserializer.class)
	private BigDecimal price;
	
	@JsonDeserialize(using = CategoryDeserializer.class)
	private String category;
	
	private String availability;
	private String condition;
	private String brand;

}
