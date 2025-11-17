package pageobejctpattern.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SetUp {

    private final WebDriverWait wait;
    WebDriver driver;

    public SetUp (WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void settings () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);
    }
}

