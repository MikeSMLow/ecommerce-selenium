@api
Feature: API User Account Management
As a backend service
I want to handle the full user lifecycle (Create, Fetch, Delete)
So that user data is accurately processed and purged

Scenario: Create, verify, and delete a user account via API
	Given the user data is prepared for "api.miguel@example.com"
	When a POST request is made to create the account
	Then the API should return a "User created!" success message
	When a GET request is made to fetch the account details for "api.miguel@example.com"
	Then the reponse should contain the user's account correct name and email
	When a DELETE request is made using the account email and password
	Then the API should return a "Account deleted!" success message