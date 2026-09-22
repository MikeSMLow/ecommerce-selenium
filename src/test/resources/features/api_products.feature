@api
Feature: API Product Catalog
  As a developer
  I want to interact with the products API
  So that I can validate backend data without a UI

  Scenario: Retrieve the full list of products
    When the user requests the product list from the API
    Then the API should return a successful response
    And the response should contain a list of products