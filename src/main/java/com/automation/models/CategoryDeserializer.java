package com.automation.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class CategoryDeserializer extends JsonDeserializer<String> {
	
	@Override
    public String deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // Read the incoming data as a flexible tree node
        JsonNode node = parser.getCodec().readTree(parser);
        
        // Scenario A: It is a complex JSON Object from the API
        if (node.isObject() && node.has("category")) {
            return node.get("category").asText(); // Extracts "Tops"
        }
        
        // Scenario B: It is a plain text string from a Cucumber UI DataTable
        return node.asText();
    }

}
