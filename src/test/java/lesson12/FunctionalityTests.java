package lesson12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FunctionalityTests {

    @Test
    public void filteringByCategory() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(25);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
    }

    @Test
    public void filteringByPrice() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(25);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");

        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        Actions actions = new Actions(driver);
        while (!driver.findElement(By.cssSelector(".faceted-slider p")).getText().equals("€16.00 - €44.00")) {
            WebElement handle = driver.findElement(By.cssSelector(".ui-slider-handle:nth-of-type(1)"));
            actions.moveToElement(handle).clickAndHold().moveByOffset(5, 0).perform();
        }

        By productContainer = By.cssSelector(".product");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productContainer));

        List<WebElement> allProductContainer = driver.findElements(productContainer);

        List<WebElement> productsInOrder = new ArrayList<>();

        for (int i = 1; i <= allProductContainer.size(); i++) {
            String locator = String.format(".product:nth-of-type(%s)", i);
            WebElement product = driver.findElement(By.cssSelector(locator));
            productsInOrder.add(product);
        }

        List<String> pricesInOrder = new ArrayList<>();
        for (WebElement product : productsInOrder) {
            String price = product.findElement(By.cssSelector(".price")).getText();
            price = price.replace("€", "").trim();
            pricesInOrder.add(price);
        }

        List <String> expectedPrices = List.of("22.94","22.94","22.94","34.46","34.80","34.80","34.80","22.68","22.68","22.68","42.00","16.68");


        System.out.println("Ceny rzeczywiste: " + pricesInOrder);
        System.out.println("Ceny oczekiwane: " + expectedPrices);
//       Assertions.assertEquals(pricesInOrder,expectedPrices);

    }

    @Test

    public void filterReset() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(25);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By categoryLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), \"Accessories\")]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
        WebElement categoryChoiceButton = driver.findElement(categoryLocator);
        categoryChoiceButton.click();

        By productFilterBox = By.cssSelector(".product");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productFilterBox));

        List<WebElement> allProductContainer = driver.findElements(productFilterBox);

        List<WebElement> productFiltered = new ArrayList<>();

        for (int i = 1; i <= allProductContainer.size(); i++) {
            String locator1 = String.format(".product:nth-of-type(%s)", i);
            WebElement product = driver.findElement(By.cssSelector(locator1));
            productFiltered.add(product);
        }
        int productCountBeforeReset = allProductContainer.size();
        System.out.println("Liczba produktów z filtrem: " + productCountBeforeReset);


        By resetLocator = By.cssSelector("#_desktop_search_filters_clear_all button");
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(resetLocator));
        resetButton.click();

        By productNoFilterBox = By.cssSelector(".product");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNoFilterBox));

        List<WebElement> allProductContainer1 = driver.findElements(productNoFilterBox);

        List<WebElement> productNoFiltered = new ArrayList<>();
        for (int i = 1; i <= allProductContainer1.size(); i++) {
            String locator2 = String.format(".product:nth-of-type(%s)", i);
            WebElement product1 = driver.findElement(By.cssSelector(locator2));
            productNoFiltered.add(product1);
        }
        int productCountAfterReset = allProductContainer1.size();
        System.out.println("Liczba produktów po resecie filtrów: " + productCountAfterReset);

    }

    @Test
    public void visibilityOfFilters() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(30);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By availableLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), \"Available\")]");
        wait.until(ExpectedConditions.elementToBeClickable(availableLocator));
        WebElement availableButton = driver.findElement(availableLocator);
        availableButton.click();

        Assertions.assertTrue(availableButton.isDisplayed());

        By paperTypeLocator = By.xpath("//label[@class='facet-label']//a[contains(text(), 'Ruled')]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paperTypeLocator));
        WebElement ruledButton = driver.findElement(availableLocator);
        ruledButton.click();


    }
}




















