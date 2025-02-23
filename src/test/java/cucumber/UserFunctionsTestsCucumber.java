package cucumber;

import cucumber.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;

import static cucumber.DriverProvider.getDriverInstance;

public class UserFunctionsTestsCucumber {

    private final SetUp setUp = new SetUp(getDriverInstance());
    private final Navigation navigation = new Navigation(getDriverInstance());
    private final Registration registration = new Registration(getDriverInstance());


    @When("I press the Sign in button on the home page")
    public void iPressTheSignInButtonOnTheHomePage() {
        registration.signInButton();
    }
    @And("I press the create account button")
    public void iPressTheCreateAccountButton() {
        registration.createAccountFieldClick();
    }
    @And("I fill out the account creation form")
    public void iFillOutTheAccountCreationForm() {
        registration.createAccountFillingOutForm();
    }
    @And("I press the Save button")
    public void iPressTheSaveButton() {
        navigation.saveButtonClick();
    }
    @And("I log in as a new user")
    public void iLogInAsANewUser() {
        registration.loggedInUser();
    }
    @Then("I see my name and surname as login confirmation")
    public void iSeeMyNameAndSurnameAsLoginConfirmation() {
        String expectedUsername = "Ewelina Nowacka";
        Assertions.assertTrue(registration.loggedInUser().contains(expectedUsername));
    }




    @And("I write the correct email address")
    public void iWriteTheCorrectEmailAddress() {
        registration.loginWriteEmail();
    }
    @And("I write the correct password")
    public void iWriteTheCorrectPassword() {
        registration.loginWritePassword();
    }
    @And("I press sing in blue button")
    public void iPressSingInBlueButton() {
        registration.signInBlueButton();
    }
    @Then("I see a login message")
    public void iSeeALoginMessage() {
        String errorMessage = registration.authorizationFailedInformation();
        Assertions.assertEquals("Authentication failed.", errorMessage);
    }



    @And("I fill out the account creation form by entering incorrect forms of data")
    public void iFillOutTheAccountCreationFormByEnteringIncorrectFormsOfData() {
        registration.fieldlessAccountFillingOutForm();
    }
    @Then("The message invalid format - last name will be displayed")
    public void theMessageInvalidFormatLastNameWillBeDisplayed() {
        registration.lastNameError();
        Assertions.assertTrue(registration.lastNameError().isDisplayed());
        Assertions.assertEquals("Invalid format.", registration.lastNameError().getText());
    }
    @And("The message invalid format - birth date will be displayed")
    public void theMessageInvalidFormatBirthDateWillBeDisplayed() {
        registration.birthdateError();
        Assertions.assertTrue(registration.birthdateError().isDisplayed());
        Assertions.assertEquals("Format should be 05/31/1970.", registration.birthdateError().getText());
    }




    @And("I write correct email address")
    public void iWriteCorrectEmailAddress() {
        registration.correctEmailField();
    }
    @And("I write incorrect email address")
    public void iWriteIncorrectEmailAddress() {
        registration.incorrectPasswordField();
    }
    @Then("Failed authorization information is displayed")
    public void failedAuthorizationInformationIsDisplayed() {
        registration.authorizationFailedInformation();
        String expectedText = "Authentication failed.";
        Assertions.assertEquals(expectedText,registration.authorizationFailedInformation());
    }



    @And("I will click on the forgot password field")
    public void iWillClickOnTheForgotPasswordField() {
        registration.forgotPasswordField();
    }
    @And("I will enter the email address needed for the reset")
    public void iWillEnterTheEmailAddressNeededForTheReset() {
        registration.emailFieldToResetPassword();
    }
    @And("I will click on the send reset link button")
    public void iWillClickOnTheSendResetLinkButton() {
        registration.sendResetLink();
    }
    @Then("I will receive information about sending a password reset link to my email")
    public void iWillReceiveInformationAboutSendingAPasswordResetLinkToMyEmail() {
        registration.confirmationMessageAfterResetPassword();
        String informationReset ="If this email address has been registered in our store, you will receive a link to reset your password at Ewelinka2024@gmail.com.";
        Assertions.assertEquals(informationReset,registration.confirmationMessageAfterResetPassword());
    }




    @Then("The window appears asking you to enter your email address")
    public void theWindowAppearsAskingYouToEnterYourEmailAddress() {
        registration.emptyEmailField();
        Assertions.assertEquals("true", registration.emptyEmailField().getAttribute("required"));
    }
    @And("The window appears asking you to enter your password address")
    public void theWindowAppearsAskingYouToEnterYourPasswordAddress() {
        registration.emptyPasswordField();
        Assertions.assertEquals("true", registration.emptyPasswordField().getAttribute("required"));
    }




    @And("I check the password field")
    public void iCheckThePasswordField() {
        registration.emptyPasswordField();
        String inputType = registration.emptyPasswordField().getAttribute("type");
        Assertions.assertEquals("password", inputType);
    }
    @Then("I enter the password and check the masking")
    public void iEnterThePasswordAndCheckTheMasking() {
        registration.emptyPasswordField().sendKeys("TestPassword123!");
        String enteredValue = registration.emptyPasswordField().getAttribute("value");
        Assertions.assertEquals("TestPassword123!", enteredValue);
    }





    @And("I click on the email field")
    public void iClickOnTheEmailField() {
        registration.emptyPasswordField();
    }
    @And("I enter the password")
    public void iEnterThePassword() {
        registration.emptyPasswordField().sendKeys("TestPassword123!");
    }
    @And("I  press the show password button")
    public void iPressTheShowPasswordButton() {
        registration.showPasswordButton();
        Assertions.assertEquals("password", registration.emptyPasswordField().getAttribute("type"));
    }
    @Then("I will see the password value")
    public void iWillSeeThePasswordValue() {
        registration.showPasswordButton().click();
        Assertions.assertEquals("text", registration.emptyPasswordField().getAttribute("type"));
    }
    @And("I will press this button again to hide the value of this field")
    public void iWillPressThisButtonAgainToHideTheValueOfThisField() {
        registration.showPasswordButton().click();
        Assertions.assertEquals("password", registration.emptyPasswordField().getAttribute("type"));
    }
}