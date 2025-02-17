package pageobejctpattern.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ActiveFiltres {

    private final WebDriverWait wait;
    WebDriver driver;


    public ActiveFiltres(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getActiveFilter() {

        By activeFiltresLocator = By.cssSelector(".filter-block");
        WebElement confirmationTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltresLocator));
        String confirmationText;
        return confirmationText = confirmationTextElement.getText();
    }

    public List<String> getFilteredProductsByPrice() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By productContainer = By.cssSelector(".product");
        wait.until(ExpectedConditions.numberOfElementsToBe(productContainer, 10));

        List<WebElement> allProducts = driver.findElements(productContainer);
        List<String> pricesInOrder = new ArrayList<>();
        for (WebElement product : allProducts) {
            String price = product.findElement(By.cssSelector(".price")).getText();
            pricesInOrder.add(price.replace("€", "").trim());
        }

        return pricesInOrder;
    }

    public List<WebElement> getAllProducts() {
        By productContainer = By.cssSelector(".product");
        return driver.findElements(productContainer);
    }

    public boolean isAccessoriesFilterVisible() {

        By activeFiltersLocator = By.cssSelector(".active-filter-title");
        boolean isAccessoriesFilterVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltersLocator)).isDisplayed();
        return isAccessoriesFilterVisible;
    }


    public void clickOnRuledFilter(){

        By ruledLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Ruled')]");
        wait.until(ExpectedConditions.elementToBeClickable(ruledLocator)).click();

    }

    public boolean isRuledFilterVisible(){

        By activeFiltersLocator2 = By.cssSelector(".active-filter-title");
        boolean isRuledFilterVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltersLocator2)).isDisplayed();
        return isRuledFilterVisible;
    }
}

