package lesson8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstSeleniumTest {


    WebDriver driver;

    @BeforeEach
    public void BeforeEach() {

        driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(10);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");

        WebElement inframeObject = driver.findElement(iframe);
        driver.switchTo().frame(inframeObject);
    }

    @Test
    public void addToCartTest() {

        By locator = By.cssSelector("input.ui-autocomplete-input");
        WebElement search = driver.findElement(locator);
        search.sendKeys("sweater");
        search.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By menulocator = By.cssSelector("li.ui-menu-item");
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(menulocator));
        firstResult.click();

        By lokator2 = By.cssSelector(".btn.btn-primary");
        WebElement search1 = driver.findElement(lokator2);
        search1.click();

        By addlocator = By.cssSelector("form#add-to-cart-or-refresh");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addlocator));
        //String confirmationText = driver.findElement(addlocator).getText();
        //String modalTitle = driver.findElement(modalTitleLocator).getText();
        //Assertions.assertTrue(confirmationText.contains("Product successfully added to your shopping cart"));;\

        String confirmationText = driver.findElement(addlocator).getText();
        System.out.println("Confirmation Text: " + confirmationText);
        Assertions.assertTrue(confirmationText.contains("ADD TO CART"));

        driver.quit();
    }

    @Test
    public void addToNewsletter() {

        By locator3 = By.cssSelector("div.input-wrapper input");
        WebElement email = driver.findElement(locator3);
        email.sendKeys("ehalec1991@gmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By subscribeLocator = By.cssSelector("input.btn.btn-primary.float-xs-right.hidden-xs-down");
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeLocator));
        firstResult.click();

        By addlocator = By.cssSelector(".alert");
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(addlocator));
        String confirmationText = confirmationMessage.getText();
        System.out.println("Potwierdzenie: " + confirmationText);
        Assertions.assertTrue(confirmationText.contains("You have successfully subscribed to this newsletter"));
    }

    @AfterEach
    public void afrerEach() {

        if (driver != null) {
            driver.quit();
        }
    }
}