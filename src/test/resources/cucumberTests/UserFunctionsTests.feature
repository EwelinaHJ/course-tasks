Feature: Checking the functionality of the website's shopping cart

  Background:
    Given Page is open

  Scenario: Creating a user account
    When I press the Sign in button on the home page
    And I press the create account button
    And I fill out the account creation form
    And I press the Save button
    And I log in as a new user
    Then  I see my name and surname as login confirmation


    Scenario: Logging in to the website with correct data
      When I press the Sign in button on the home page
      And I write the correct email address
      And I write the correct password
      And I press sing in blue button
      Then I see a login message


    Scenario: Fieldless registration test
      When  I press the Sign in button on the home page
      And  I press the create account button
      And I fill out the account creation form by entering incorrect forms of data
      And I press the Save button
      Then The message invalid format - last name will be displayed
      And The message invalid format - birth date will be displayed


    Scenario: Logging into your account using incorrect details
      When I press the Sign in button on the home page
      And I write correct email address
      And I write incorrect email address
      And I press the Save button
      Then Failed authorization information is displayed


    Scenario: Password reset
      When I press the Sign in button on the home page
      And I will click on the forgot password field
      And I will enter the email address needed for the reset
      And I will click on the send reset link button
      Then I will receive information about sending a password reset link to my email

    Scenario: Login without completed fields
      When I press the Sign in button on the home page
      And I press the Save button
      Then The window appears asking you to enter your email address
      And  The window appears asking you to enter your password address


    Scenario:Checking whether the password is masked
      When I press the Sign in button on the home page
      And I check the password field
      Then I enter the password and check the masking


    Scenario: Checking whether the password is visible after clicking the show button
      When I press the Sign in button on the home page
      And I click on the email field
      And I enter the password
      And I  press the show password button
      Then I will see the password value
      And I  press this button again to hide the value of this field