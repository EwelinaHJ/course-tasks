package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static cucumber.DriverProvider.getDriverInstance;


public class Filters {

    private final WebDriver driver = getDriverInstance();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public Filters () {
    }

    public void selectAccessoriesFIlter() {

        By categoryLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), \"Accessories\")]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
        WebElement categoryChoiceButton = driver.findElement(categoryLocator);
        categoryChoiceButton.click();
    }

    public void selectPriceFilter() {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".faceted-slider")));

        Actions actions = new Actions(driver);
        String sliderValue;
        do {
            WebElement handle = driver.findElement(By.cssSelector(".ui-slider-handle:nth-of-type(1)"));
            actions.moveToElement(handle)
                    .clickAndHold()
                    .moveByOffset(5, 0)
                    .release()
                    .perform();
            sliderValue = driver.findElement(By.cssSelector(".faceted-slider p")).getText();
        } while (!sliderValue.equals("€16.00 - €44.00"));


        By spinnerLocator1 = By.cssSelector(".spinner");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator1));
    }

    public void selectFilterAccessories() {

        By categoryLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Accessories')]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator)).click();
    }
}