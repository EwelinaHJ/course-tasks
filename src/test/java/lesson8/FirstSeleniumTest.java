package lesson8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(addlocator));
        String confirmationText = confirmationMessage.getText();
        System.out.println("Potwierdzenie: " + confirmationText);

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
    }

    @AfterEach
    public void afrerEach() {

        if (driver != null) {
            driver.quit();
        }
    }
}