package pageobejctpattern.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobejctpattern.tests.pages.Navigation;
import pageobejctpattern.tests.pages.Registration;

import java.time.Duration;

public class UserFunctionsTests {

    ChromeDriver driver;
    WebDriverWait wait;

    private Registration registration;
    private Navigation navigation;

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(30);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);
    }

    @Test
    public void newUserRegistrationTest() {

        registration = new Registration(driver);
        navigation = new Navigation(driver);

        registration.signInButton();
        registration.createAccountFieldClick();
        registration.createAccountFillingOutForm();
        navigation.saveButtonClick();
        registration.loggedInUser();
        String expectedUsername = "Ewelina Nowacka";

        Assertions.assertTrue(registration.loggedInUser().contains(expectedUsername));
    }

    @Test
    public void loginCorrectDataTest() {

        registration = new Registration(driver);
        registration.signInButton();
        registration.loginWriteEmail();
        registration.loginWritePassword();
        registration.signInBlueButton();

        String errorMessage = registration.authorizationFailedInformation();
        Assertions.assertEquals("Authentication failed.", errorMessage);
    }

    @Test
    public void fieldlessRegistrationTest() {

        registration = new Registration(driver);
        navigation = new Navigation(driver);

        registration.signInButton();
        registration.createAccountFieldClick();
        registration.fieldlessAccountFillingOutForm();
        navigation.saveButtonClick();
        registration.lastNameError();

        Assertions.assertTrue(registration.lastNameError().isDisplayed());
        Assertions.assertEquals("Invalid format.", registration.lastNameError().getText());

        registration.birthdateError();

        Assertions.assertTrue(registration.birthdateError().isDisplayed());
        Assertions.assertEquals("Format should be 05/31/1970.", registration.birthdateError().getText());
    }

    @Test
    public void loginIncorrectDataTest() {

        registration = new Registration(driver);

        registration.signInButton();
        registration.correctEmailField();
        registration.incorrectPasswordField();
        registration.signInBlueButton();
        registration.authorizationFailedInformation();

        String expectedText = "Authentication failed.";

        Assertions.assertEquals(expectedText,registration.authorizationFailedInformation());
    }

    @Test
    public void passwordResetTest() {

        registration = new Registration(driver);
        registration.signInButton();
        registration.forgotPasswordField();
        registration.emailFieldToResetPassword();
        registration.sendResetLink();
        registration.confirmationMessageAfterResetPassword();

        String informationReset ="If this email address has been registered in our store, you will receive a link to reset your password at Ewelinka2024@gmail.com.";

        Assertions.assertEquals(informationReset,registration.confirmationMessageAfterResetPassword());
    }

    @Test

    public void loginWithoutCompletedFieldsTest() {

        registration = new Registration(driver);

        registration.signInButton();
        registration.signInBlueButton();
        registration.emptyEmailField();

        Assertions.assertEquals("true", registration.emptyEmailField().getAttribute("required"));

        registration.emptyPasswordField();
        Assertions.assertEquals("true", registration.emptyPasswordField().getAttribute("required"));
    }

    @Test
    public void checkIfPasswordIsMaskedTest() {

        registration = new Registration(driver);

        registration.signInButton();
        registration.emptyPasswordField();

        String inputType = registration.emptyPasswordField().getAttribute("type");
        Assertions.assertEquals("password", inputType);

        registration.emptyPasswordField().sendKeys("TestPassword123!");
        String enteredValue = registration.emptyPasswordField().getAttribute("value");
        Assertions.assertEquals("TestPassword123!", enteredValue);
    }

    @Test
    public void checkShowPasswordButtonTest() {

        registration = new Registration(driver);
        registration.signInButton();
        registration.emptyPasswordField();
        registration.emptyPasswordField().sendKeys("TestPassword123!");
        registration.showPasswordButton();

        Assertions.assertEquals("password", registration.emptyPasswordField().getAttribute("type"));

        registration.showPasswordButton().click();
        Assertions.assertEquals("text", registration.emptyPasswordField().getAttribute("type"));

        registration.showPasswordButton().click();
        Assertions.assertEquals("password", registration.emptyPasswordField().getAttribute("type"));
    }

    @AfterEach

    public void afterEach() {

        driver.quit();
    }
}   