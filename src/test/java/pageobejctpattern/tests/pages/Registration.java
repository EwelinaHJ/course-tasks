package pageobejctpattern.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Registration {

    private final WebDriverWait wait;
    WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void signInButton(){

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();
    }

    public void createAccountFieldClick(){

        By accountLocator = By.cssSelector(".no-account");
        WebElement createAccount = wait.until(ExpectedConditions.elementToBeClickable(accountLocator));
        createAccount.click();
    }

    public void createAccountFillingOutForm(){


        By mrs = By.xpath("//label[@class=\'radio-inline\'][@for=\'field-id_gender-2\']");
        WebElement genderChoice = driver.findElement(mrs);
        genderChoice.click();

        By nameField = By.cssSelector("#field-firstname");
        WebElement firstName = driver.findElement(nameField);
        firstName.sendKeys("Ewelina");

        By nameSecondField = By.cssSelector("#field-lastname");
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameSecondField));
        lastName.sendKeys("Nowacka");

        String email = "test" + System.currentTimeMillis() + "@test.com";

        By emailField = By.cssSelector("#field-email");
        WebElement emailadress = driver.findElement(emailField);
        emailadress.sendKeys(email);

        By passwordField = By.cssSelector("#field-password");
        WebElement password = driver.findElement(passwordField);
        password.sendKeys("EwelinkaNowacka78460!");

        By birthdateField = By.cssSelector("#field-birthday");
        WebElement birthdate = driver.findElement(birthdateField);
        birthdate.sendKeys("09/09/1990");

        By square1 = By.xpath("//input[@name='optin']");
        WebElement squareChoice1 = driver.findElement(square1);
        squareChoice1.click();

        By square2 = By.xpath("//input[@name='psgdpr']");
        WebElement squareChoice2 = driver.findElement(square2);
        squareChoice2.click();

        By square3 = By.xpath("//input[@name='newsletter']");
        WebElement squareChoice3 = driver.findElement(square3);
        squareChoice3.click();

        By square4 = By.xpath("//input[@name='customer_privacy']");
        WebElement squareChoice4 = driver.findElement(square4);
        squareChoice4.click();

    }
    public String loggedInUser(){

        By loggedInUser = By.xpath("//span[@class=\'hidden-sm-down\'][contains (text(), \'Ewelina Nowacka')]");
        WebElement confirmation = driver.findElement(loggedInUser);
        String confirmationText = confirmation.getText();
        return confirmationText;
    }
    public WebElement loginWriteEmail(){

        String email = "test" + System.currentTimeMillis() + "@test.com";

        By emailField = By.cssSelector("#field-email");
        WebElement emailSecond = driver.findElement(emailField);
        emailSecond.sendKeys(email);
        return emailSecond;
    }

    public WebElement loginWritePassword(){

        By passwordField = By.cssSelector("#field-password");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        password.sendKeys("EwelinkaNowacka78460!");
        return password;
    }

    public void signInBlueButton(){

        By signInBlue = By.cssSelector("#submit-login");
        WebElement signInBlueButton = wait.until(ExpectedConditions.elementToBeClickable(signInBlue));
        signInBlueButton.click();
    }

    public void fieldlessAccountFillingOutForm(){

        By mrs = By.xpath("//label[@class=\'radio-inline\'][@for=\'field-id_gender-2\']");
        WebElement genderChoice = driver.findElement(mrs);
        genderChoice.click();

        By nameField = By.cssSelector("#field-firstname");
        WebElement firstName = driver.findElement(nameField);
        firstName.sendKeys("Ewelina");

        By nameSecondField = By.cssSelector("#field-lastname");
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameSecondField));
        lastName.sendKeys("3456789");

        By emailField = By.cssSelector("#field-email");
        WebElement email = driver.findElement(emailField);
        email.sendKeys("Ewelink2024@gmail.com");

        By passwordField = By.cssSelector("#field-password");
        WebElement password = driver.findElement(passwordField);
        password.sendKeys("EwelinkaNowacka78460!");

        By birthdateField = By.cssSelector("#field-birthday");
        WebElement birthdate = driver.findElement(birthdateField);
        birthdate.sendKeys("ZZ/09/n99w");

        By square1 = By.xpath("//input[@name='optin']");
        WebElement squareChoice1 = driver.findElement(square1);
        squareChoice1.click();

        By square2 = By.xpath("//input[@name='psgdpr']");
        WebElement squareChoice2 = driver.findElement(square2);
        squareChoice2.click();

        By square3 = By.xpath("//input[@name='newsletter']");
        WebElement squareChoice3 = driver.findElement(square3);
        squareChoice3.click();

        By square4 = By.xpath("//input[@name='customer_privacy']");
        WebElement squareChoice4 = driver.findElement(square4);
        squareChoice4.click();
    }

    public WebElement lastNameError(){


        By lastnameError = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Invalid format\')]");
        WebElement lastnameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameError));
        return lastnameErrorMessage;
    }

    public WebElement birthdateError() {

        By birthdateError = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Format should be 05/31/1970')]");
        WebElement birthdateErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(birthdateError));
        return birthdateErrorMessage;
    }

    public WebElement correctEmailField(){

        By emailField = By.cssSelector("#field-email");
        WebElement email = driver.findElement(emailField);
        email.sendKeys("Ewelinka2024@gmail.com");
        return email;
    }

    public WebElement incorrectPasswordField(){


        By passwordField = By.cssSelector("#field-password");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        password.sendKeys("EwelinkaNowacka7846xd!");
        return password;
    }

    public  String authorizationFailedInformation(){


        By redField = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Authentication failed\')]");
        WebElement confirmationText = wait.until(ExpectedConditions.visibilityOfElementLocated(redField));
        String loginInvalidText = confirmationText.getText();
        return loginInvalidText;
    }

    public WebElement forgotPasswordField(){

        By forgotPassword = By.cssSelector(".forgot-password");
        WebElement forgotPasswordButton = driver.findElement(forgotPassword);
        forgotPasswordButton.click();
        return forgotPasswordButton;
    }

    public WebElement emailFieldToResetPassword(){

        By emailField = By.xpath("//input[@type=\'email\'][@name=\'email\'][@id=\'email\']");
        WebElement emailAdress = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailAdress.sendKeys("Ewelinka2024@gmail.com");
        return emailAdress;
    }

    public void sendResetLink (){

        By sendResetLink = By.cssSelector("#send-reset-link");
        WebElement resetLinkButton = driver.findElement(sendResetLink);
        resetLinkButton.click();

    }

    public String confirmationMessageAfterResetPassword(){

        By greenField = By.cssSelector(".ps-alert-success");
        WebElement confirmationField = wait.until(ExpectedConditions.visibilityOfElementLocated(greenField));
        String confirmationMessage = confirmationField.getText();
        return confirmationMessage;
    }

    public WebElement emptyEmailField(){

        By emailField = By.cssSelector("#field-email");
        WebElement email = driver.findElement(emailField);
        return email;
    }

    public WebElement emptyPasswordField(){

        By passwordField = By.cssSelector("#field-password");
        WebElement password = driver.findElement(passwordField);
        return password;
    }

    public WebElement showPasswordButton(){

        By showPasswordButton = By.xpath("//span[@class=\'input-group-btn\']/button");
        WebElement showPassword = wait.until(ExpectedConditions.elementToBeClickable(showPasswordButton));
        return showPassword;
    }
}
