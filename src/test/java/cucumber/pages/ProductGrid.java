package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductGrid {

    private final WebDriverWait wait;
    WebDriver driver;
    private By totalProductsCounter  = By.cssSelector(".total-products");
    private By productBox =  By.cssSelector(".product");
    private String totalProductsText = "There are 19 products.";
    private By currentPage =  By.cssSelector(".page-list .current");
    private By nextPageButton = By.cssSelector("li a[rel='next']");

    public ProductGrid(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickOnAllProductsButton() {

        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();
    }
    public void selectProduct(){

        By productLocator = By.xpath("//article[@data-id-product='1']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();
    }

    public void selectTshirt(){

        By productLocator = By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();
    }

    public void comingBackToMainPage() throws InterruptedException {

        Thread.sleep(1000);
        By myStore = By.cssSelector("#_desktop_logo a");
        WebElement myStoreButton = wait.until(ExpectedConditions.elementToBeClickable(myStore));
        myStoreButton.click();
    }

    public int totalProductsCounter(){

        String totalProductsText = wait.until(ExpectedConditions.visibilityOfElementLocated(totalProductsCounter)).getText();
        int expectedTotalProducts = Integer.parseInt(totalProductsText.replaceAll("[^0-9]", ""));
        return expectedTotalProducts;
    }

    public int productsOn1Page(){

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int page1Count = driver.findElements(productBox).size();
        return page1Count;
    }

    public int productsOn2Page(){

        wait.until(ExpectedConditions.textToBe(currentPage, "2"));
        return driver.findElements(productBox).size();

    }
    public int amountOfFilteredProducts(){

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(totalProductsCounter, totalProductsText)));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int filteredProductCount = driver.findElements(productBox).size();
        return filteredProductCount;
    }

    public int productsAfterReset() {

        wait.until(ExpectedConditions.textToBe(totalProductsCounter, totalProductsText));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int firstPageAfterReset = driver.findElements(productBox).size();

        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
        wait.until(ExpectedConditions.textToBe(currentPage, "2"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int secondPageAfterReset = driver.findElements(productBox).size();

        int totalAfterReset = firstPageAfterReset + secondPageAfterReset;
        return totalAfterReset;
    }
}