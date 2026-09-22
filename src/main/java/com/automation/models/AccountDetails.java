package com.automation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {
	private String title;
    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobile;
}
