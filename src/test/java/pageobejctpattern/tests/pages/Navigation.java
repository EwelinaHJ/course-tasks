package pageobejctpattern.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Navigation {

    private final WebDriverWait wait;
    WebDriver driver;


    public Navigation(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void refreshingPage() {
        driver.navigate().refresh();
        driver.switchTo().frame("framelive");
    }

    public void saveButtonClick() {

        By save = By.xpath("//button[@type=\"submit\"]");
        WebElement saveButton = driver.findElement(save);
        saveButton.click();
    }

    public void goingToSecondPage(){


        By nextPageButton = By.cssSelector("li a[rel='next']");
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
        By productBox = By.cssSelector(".product");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
    }

    public void reset(){
        By resetLocator = By.cssSelector("#_desktop_search_filters_clear_all button");
        wait.until(ExpectedConditions.elementToBeClickable(resetLocator)).click();
    }
}