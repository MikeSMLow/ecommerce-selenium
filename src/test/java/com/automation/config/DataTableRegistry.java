package com.automation.config;

import com.automation.models.AccountDetails;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableRegistry {
	
	@DataTableType
    public AccountDetails accountDetailsEntry(DataTable table) {
Map<String, String> entry = new HashMap<>();
        
        // .cells() returns the raw List<List<String>> without triggering any Jackson converters
        for (List<String> row : table.cells()) {
            entry.put(row.get(0), row.get(1));
        }
		
        return AccountDetails.builder()
                .title(entry.get("Title"))
                .password(entry.get("Password"))
                .day(entry.get("Day"))
                .month(entry.get("Month"))
                .year(entry.get("Year"))
                .firstName(entry.get("First Name"))
                .lastName(entry.get("Last Name"))
                .address(entry.get("Address"))
                .country(entry.get("Country"))
                .state(entry.get("State"))
                .city(entry.get("City"))
                .zipcode(entry.get("Zipcode"))
                .mobile(entry.get("Mobile"))
                .build();
    }
}
