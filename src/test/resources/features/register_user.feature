Feature: User Registration
As a new visitor
I want to create an account on Automation Exercise
So that I can access user-specific features

Scenario: Initiate new user signup
Given the user is on the login and signup page
When the user signs up with name "Miguel" and email "miguel.test@example.com"
Then the user should be directed to the account details page
When the user fills the account details with the following data:
      | Title       | Mr.             |
      | Password    | SecurePass123!  |
      | Day         | 15              |
      | Month       | May             |
      | Year        | 1990            |
      | First Name  | Miguel          |
      | Last Name   | Sanchez         |
      | Address     | 123 Main St     |
      | Country     | United States   |
      | State       | Texas           |
      | City        | Austin          |
      | Zipcode     | 78701           |
      | Mobile      | 5551234567      |
And the user clicks the create account button
Then a successful account creation message should be displayed