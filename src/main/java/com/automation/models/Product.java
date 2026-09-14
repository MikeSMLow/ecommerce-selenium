package com.automation.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private String name;
	private int quantity;
	private BigDecimal price;
	private String category;
	private String availability;
	private String condition;
	private String brand;

}
