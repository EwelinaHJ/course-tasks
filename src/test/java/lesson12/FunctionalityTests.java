package lesson12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FunctionalityTests {

    @Test
    public void filteringByCategoryTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(25);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By categoryLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), \"Accessories\")]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
        WebElement categoryChoiceButton = driver.findElement(categoryLocator);
        categoryChoiceButton.click();

        By activeFiltresLocator = By.cssSelector(".filter-block");
        WebElement confirmationTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltresLocator));
        String confirmationText = confirmationTextElement.getText();
        System.out.println("Confirmation Text:" + confirmationText);

        String expectedText = "Categories: Accessories";

        Assertions.assertTrue(confirmationText.contains(expectedText), "Text not found");

        driver.quit();
    }

    @Test
    public void filteringByPriceTest() {

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        driver.get("https://demo.prestashop.com/#/en/front");

        WebElement iframeObject = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframeObject);

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

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

        By productContainer = By.cssSelector(".product");
        wait.until(ExpectedConditions.numberOfElementsToBe(productContainer, 10));

        List<WebElement> allProducts = driver.findElements(productContainer);

        List<String> pricesInOrder = new ArrayList<>();
        for (WebElement product : allProducts) {
            String price = product.findElement(By.cssSelector(".price")).getText();
            pricesInOrder.add(price.replace("€", "").trim());
        }

        Assertions.assertEquals(10, allProducts.size(), "Nieprawidłowa liczba produktów po filtrowaniu");

        System.out.println("Znalezione ceny: " + pricesInOrder);

        driver.quit();
    }

    @Test
    public void filterResetTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(30);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By allProductsLocator = By.cssSelector(".all-product-link");
        wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator)).click();

        By totalProductsCounter = By.cssSelector(".total-products");
        String totalProductsText = wait.until(ExpectedConditions.visibilityOfElementLocated(totalProductsCounter)).getText();
        int expectedTotalProducts = Integer.parseInt(totalProductsText.replaceAll("[^0-9]", ""));
        System.out.println("Oczekiwana całkowita liczba produktów (z licznika): " + expectedTotalProducts);

        By productBox = By.cssSelector(".product");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int page1Count = driver.findElements(productBox).size();
        System.out.println("Liczba produktów na stronie 1: " + page1Count);


        By nextPageButton = By.cssSelector("li a[rel='next']");
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();

        By currentPage = By.cssSelector(".page-list .current");
        wait.until(ExpectedConditions.textToBe(currentPage, "2"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));

        int page2Count = driver.findElements(productBox).size();
        System.out.println("Liczba produktów na stronie 2: " + page2Count);

        int totalInitialCount = page1Count + page2Count;
        System.out.println("Całkowita początkowa liczba produktów: " + totalInitialCount);

        Assertions.assertEquals(expectedTotalProducts, totalInitialCount,
                "Suma produktów z obu stron powinna być zgodna z licznikiem");

        By prevPageButton = By.cssSelector("li a[rel='prev']");
        wait.until(ExpectedConditions.elementToBeClickable(prevPageButton)).click();
        wait.until(ExpectedConditions.textToBe(currentPage, "1"));

        By categoryLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Accessories')]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator)).click();


        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(totalProductsCounter, totalProductsText)));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int filteredProductCount = driver.findElements(productBox).size();
        System.out.println("Liczba produktów po filtrowaniu: " + filteredProductCount);

        By resetLocator = By.cssSelector("#_desktop_search_filters_clear_all button");
        wait.until(ExpectedConditions.elementToBeClickable(resetLocator)).click();

        wait.until(ExpectedConditions.textToBe(totalProductsCounter, totalProductsText));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int firstPageAfterReset = driver.findElements(productBox).size();

        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
        wait.until(ExpectedConditions.textToBe(currentPage, "2"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBox));
        int secondPageAfterReset = driver.findElements(productBox).size();

        int totalAfterReset = firstPageAfterReset + secondPageAfterReset;
        System.out.println("Całkowita liczba produktów po resecie: " + totalAfterReset);

        Assertions.assertTrue(filteredProductCount < expectedTotalProducts,
                "Liczba produktów po filtrowaniu powinna być mniejsza niż początkowa");
        Assertions.assertEquals(expectedTotalProducts, totalAfterReset,
                "Liczba produktów po resecie powinna być równa liczbie z licznika");

        driver.quit();
    }

    @Test
    public void checkTwoFiltersVisibilityTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(25);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By allProductsLocator = By.cssSelector(".all-product-link");
        wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator)).click();

        By accessoriesLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Accessories')]");
        wait.until(ExpectedConditions.elementToBeClickable(accessoriesLocator)).click();


        By activeFiltersLocator = By.cssSelector(".active-filter-title");
        boolean isAccessoriesFilterVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltersLocator)).isDisplayed();
        System.out.println("Czy filtr Accessories jest widoczny: " + isAccessoriesFilterVisible);

        By ruledLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Ruled')]");
        wait.until(ExpectedConditions.elementToBeClickable(ruledLocator)).click();

        By activeFiltersLocator2 = By.cssSelector(".active-filter-title");
        boolean isRuledFilterVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(activeFiltersLocator2)).isDisplayed();
        System.out.println("Czy filtr Accessories jest widoczny: " + isRuledFilterVisible);

        driver.quit();
    }
}
