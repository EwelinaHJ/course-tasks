Feature: Check product filtering

  Background:
      Given Page is open


  Scenario: Pressing the accessories filter
    When I will click the All Products button
    And I will select the accessories filter and press it
    Then I will see text confirming the filter has been applied
