Feature: Product Catalog & Cart Operations
  As a shopper
  I want to browse and add products to my cart
  So that I can purchase them later

  Background:
    Given the user navigates to the home page

  Scenario: Add a specific product to the cart from the home page
    When the user adds "Blue Top" to the cart
    Then the product should be added successfully

  Scenario: Verify product details displayed on the product card
    Then the product card for "Men Tshirt" should display the following details:
      | name       | price |
      | Men Tshirt | 400.0 |
      