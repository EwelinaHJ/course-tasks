package lesson12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserFunctionsTests {

    @Test
    public void newUserRegistrationTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        By accountLocator = By.cssSelector(".no-account");
        WebElement createAccount = wait.until(ExpectedConditions.elementToBeClickable(accountLocator));
        createAccount.click();

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

        By save = By.xpath("//button[@type=\"submit\"]");
        WebElement saveButton = driver.findElement(save);
        saveButton.click();

        By loggedInUser = By.xpath("//span[@class=\'hidden-sm-down\'][contains (text(), \'Ewelina Nowacka')]");
        WebElement confirmation = driver.findElement(loggedInUser);
        String confirmationText = confirmation.getText();
        System.out.println("Zalogowany użytkownik:" + confirmationText);

        driver.quit();


    }

    @Test
    public void loginCorrectDataTest() {


        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        String email = "test" + System.currentTimeMillis() + "@test.com";

        By emailField = By.cssSelector("#field-email");
        WebElement emailSecond = driver.findElement(emailField);
        emailSecond.sendKeys(email);

        By passwordField = By.cssSelector("#field-password");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        password.sendKeys("EwelinkaNowacka78460!");

        By signInBlue = By.cssSelector("#submit-login");
        WebElement signInBlueButton = wait.until(ExpectedConditions.elementToBeClickable(signInBlue));
        signInBlueButton.click();

        driver.quit();
    }

    @Test
    public void fieldlessRegistrationTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        By accountLocator = By.cssSelector(".no-account");
        WebElement createAccount = wait.until(ExpectedConditions.elementToBeClickable(accountLocator));
        createAccount.click();

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

        By save = By.xpath("//button[@type=\"submit\"]");
        WebElement saveButton = driver.findElement(save);
        saveButton.click();

        By lastnameError = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Invalid format\')]");
        WebElement lastnameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameError));
        Assertions.assertTrue(lastnameErrorMessage.isDisplayed());
        Assertions.assertEquals("Invalid format.", lastnameErrorMessage.getText());

        By birthdateError = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Format should be 05/31/1970')]");
        WebElement birthdateErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(birthdateError));
        Assertions.assertTrue(birthdateErrorMessage.isDisplayed());
        Assertions.assertEquals("Format should be 05/31/1970.", birthdateErrorMessage.getText());

        System.out.println("Błąd nazwiska: " + lastnameErrorMessage.getText());
        System.out.println("Błąd daty urodzenia: " + birthdateErrorMessage.getText());

        driver.quit();

    }

    @Test
    public void loginIncorrectDataTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        By emailField = By.cssSelector("#field-email");
        WebElement email = driver.findElement(emailField);
        email.sendKeys("Ewelinka2024@gmail.com");

        By passwordField = By.cssSelector("#field-password");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        password.sendKeys("EwelinkaNowacka7846xd!");

        By signInBlue = By.cssSelector("#submit-login");
        WebElement signInBlueButton = wait.until(ExpectedConditions.elementToBeClickable(signInBlue));
        signInBlueButton.click();

        By redField = By.xpath("//li[@class=\'alert alert-danger\'][contains(text(),\'Authentication failed\')]");
        WebElement confirmationText = wait.until(ExpectedConditions.visibilityOfElementLocated(redField));
        String loginInvalidText = confirmationText.getText();
        System.out.println("Tekst po błędnym zalogowaniu:" + loginInvalidText);

        driver.quit();
    }

    @Test
    public void passwordResetTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        By forgotPassword = By.cssSelector(".forgot-password");
        WebElement forgotPasswordButton = driver.findElement(forgotPassword);
        forgotPasswordButton.click();

        By emailField = By.xpath("//input[@type=\'email\'][@name=\'email\'][@id=\'email\']");
        WebElement emailAdress = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailAdress.sendKeys("Ewelinka2024@gmail.com");

        By sendResetLink = By.cssSelector("#send-reset-link");
        WebElement resetLinkButton = driver.findElement(sendResetLink);
        resetLinkButton.click();

        By greenField = By.cssSelector(".ps-alert-success");
        WebElement confirmationField = wait.until(ExpectedConditions.visibilityOfElementLocated(greenField));
        String confirmationMessage = confirmationField.getText();

        System.out.println("Informacja po resecie:" + confirmationMessage);

        driver.quit();

    }

    @Test

    public void loginWithoutCompletedFieldsTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class=\'hidden-sm-down\'][contains(text(), \'Sign in\')]");
        WebElement singInButton = driver.findElement(signIn);
        singInButton.click();

        By signInBlue = By.cssSelector("#submit-login");
        WebElement signInBlueButton = wait.until(ExpectedConditions.elementToBeClickable(signInBlue));
        signInBlueButton.click();

        By emailField = By.cssSelector("#field-email");
        WebElement email = driver.findElement(emailField);
        Assertions.assertEquals("true", email.getAttribute("required"));

        By passwordField = By.cssSelector("#field-password");
        WebElement password = driver.findElement(passwordField);
        Assertions.assertEquals("true", password.getAttribute("required"));

        driver.quit();

    }

    @Test
    public void checkIfPasswordIsMaskedTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By signIn = By.xpath("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]");
        wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();

        By passwordField = By.cssSelector("#field-password");
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));

        String inputType = password.getAttribute("type");
        Assertions.assertEquals("password", inputType);

        password.sendKeys("TestPassword123!");
        String enteredValue = password.getAttribute("value");

        System.out.println("Typ pola: " + inputType);
        System.out.println("Wprowadzona wartość: " + enteredValue);

        driver.quit();
    }


        @Test
        public void checkShowPasswordButtonTest() {
            ChromeDriver driver = new ChromeDriver();
            Duration timeToWait = Duration.ofSeconds(40);
            driver.manage().timeouts().implicitlyWait(timeToWait);
            driver.get("https://demo.prestashop.com/#/en/front");

            By iframe = By.id("framelive");
            WebElement iframeObject = driver.findElement(iframe);
            driver.switchTo().frame(iframeObject);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            By spinnerLocator = By.id("loadingMessage");
            ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
            wait.until(elementInvisible);

            By signIn = By.xpath("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]");
            wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();

            By passwordField = By.cssSelector("#field-password");
            WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
            password.sendKeys("TestPassword123!");

            By showPasswordButton = By.xpath("//span[@class=\'input-group-btn\']/button");
            WebElement showPassword = wait.until(ExpectedConditions.elementToBeClickable(showPasswordButton));

            Assertions.assertEquals("password", password.getAttribute("type"));

            showPassword.click();
            Assertions.assertEquals("text", password.getAttribute("type"));

            showPassword.click();
            Assertions.assertEquals("password", password.getAttribute("type"));

            System.out.println("Test przebiegł pomyślnie - przycisk show password działa prawidłowo");

            driver.quit();
        }
    }