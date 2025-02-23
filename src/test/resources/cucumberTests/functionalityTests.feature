Feature: Check product filtering

  Background:
    Given Page is open

    Scenario: Pressing the accessories filter
      When I will click the All Products button
      And I will select the accessories filter and press it
      Then I will see text confirming the filter has been applied

    Scenario: Filtering products by price
      When I will click the All Products button
      And I select the price filter and set the slider to a specific price range
      Then Products whose price is within the set price range are displayed


    Scenario: Filter reset
      When I will click the All Products button
      And I get the total number of products
      And I count the products on the first page
      And I'm going to the other page and count products
      And I applies the accessories filter
      Then I press filter reset and count the products


    Scenario: Using two filters
      When I will click the All Products button
      And  I will select the accessories filter and press it
      And I check the filter application
      And I choose another filter: Ruled
      Then  I check the second filter application