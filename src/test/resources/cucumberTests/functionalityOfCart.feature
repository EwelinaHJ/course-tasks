Feature: Checking the functionality of the website's shopping cart

  Background:
    Given Page is open

  Scenario: Successfully adding product to cart
    When I select a product from the product list
    And I click add to the cart button
    Then I should see message "Product successfully added to your shopping cart" on the modal

  Scenario: Checking the number of products in the basket
    When I am on the product grid page
    And  I select a T-shirt
    And I click add to the cart button and add product to the cart
    And I proceed to checkout
    Then the cart should contain 1 item


  Scenario: Removing product from the cart
    When I am on the product grid page
    And  I select a T-shirt
    And I click add to the cart button and add product to the cart
    And I proceed to checkout
    And I remove product from the cart
    Then I get confirmation  that the product has been removed from the cart


  Scenario: Checking the passage to the checkout
    When I am on the product grid page
    And  I select a T-shirt
    And I click add to the cart button and add product to the cart
    And I proceed to checkout
    And I click second proceed to checkout button
    Then the checkout form is displayed

  Scenario: Checking the price after increasing the quantity of the product
    When I am on the product grid page
    And  I select a T-shirt
    And I click add to the cart button and add product to the cart
    And I proceed to checkout
    And I increase the amount of the product
    Then I check whether the price has changed and is consistent


    Scenario: Checking the status of the cart after going to the home page
      When I am on the product grid page
      And  I select a T-shirt
      And I click add to the cart button and add product to the cart
      And I proceed to checkout
      And I come back to the home page
      And I entry again to the cart
      Then I check if there is the same product in the basket

    Scenario: Checking the quantity of the product in the cart after refreshing the page
      When I select one product
      And I add the product to the cart
      And I press the proceed to checkout button
      And I refresh the page
      Then I check how many products are in the cart after refreshing the page



