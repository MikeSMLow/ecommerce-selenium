package com.automation.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class PriceDeserializer extends JsonDeserializer<BigDecimal> {
	
	@Override
    public BigDecimal deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // 1. Extract the raw string from the API JSON (e.g., "Rs. 500")
        String rawPrice = parser.getText();
        
        // 2. Strip the currency and whitespace
        String cleanPrice = rawPrice.replace("Rs.", "").trim();
        
        // 3. Return the mathematically pure BigDecimal
        return new BigDecimal(cleanPrice);
    }

}
