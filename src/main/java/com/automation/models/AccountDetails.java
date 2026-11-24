package com.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {
	private String name;
	private String title;
    private String password;
    private String day;
    private String month;
    private String year;
    
    @JsonProperty("firstname")
    private String firstName;
    
    @JsonProperty("lastname")
    private String lastName;
    
    private String email;
    
    @JsonProperty("address1")
    private String address;
    
    private String country;
    private String state;
    private String city;
    private String zipcode;
    
    @JsonProperty("mobile_number")
    private String mobile;
}
