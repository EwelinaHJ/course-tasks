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

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalityOfCartTests {

    @Test
    public void addingProductToCartTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(20);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By productLocator = By.xpath("//article[@data-id-product='1']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLokator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLokator));
        addToCart.click();

        By confirmationTextLocator = By.cssSelector("#myModalLabel");
        WebElement confirmationTextElement = wait.until(ExpectedConditions.elementToBeClickable(confirmationTextLocator));
        String confirmationText = confirmationTextElement.getText();
        System.out.println("Confirmation Text:" + confirmationText);

        String expectedText = "Product successfully added to your shopping cart";

        assertTrue(confirmationText.contains(expectedText), "Text not found");

        driver.quit();
    }


    @Test
    public void quanityOfProductTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
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

        By productLocator = By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By quantitiyLocator = By.cssSelector(".input-group input");
        WebElement cartItemCountElement = driver.findElement(quantitiyLocator);

        String itemCountText = cartItemCountElement.getAttribute("value");
        int itemCount = Integer.parseInt(itemCountText.trim());
        int expectedCount = 1;

        assertEquals(expectedCount, itemCount, "Liczba w koszyku jest niezgodna z oczekiwaną");

        driver.quit();
    }

    @Test
    public void removingTheProductTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
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

        By productLocator = By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By removingLocator = By.cssSelector(".cart-line-product-actions .remove-from-cart");
        WebElement removingButton = wait.until(ExpectedConditions.elementToBeClickable(removingLocator));
        removingButton.click();

        By emptyCartMessage = By.cssSelector(".cart-overview.js-cart .no-items");
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        assertEquals("There are no more items in your cart", message.getText().trim());

        driver.quit();
    }


    @Test
    public void proceedToCheckoutTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
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

        By productLocator = By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By proceedToCheckoutLocator2 = By.cssSelector("div.checkout  div.text-sm-center");
        WebElement proceedToCheckoutButton2 = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator2));
        proceedToCheckoutButton2.click();

        By checkoutFormLocator = By.id("checkout-personal-information-step");
        WebElement checkoutForm = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutFormLocator));
        Assertions.assertTrue(checkoutForm.isDisplayed());

        driver.quit();
    }

    @Test
    public void priseCheckingTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By productLocator = By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By addingProduct = By.cssSelector(".js-increase-product-quantity");
        WebElement upArrowButton = wait.until(ExpectedConditions.elementToBeClickable(addingProduct));
        upArrowButton.click();
        By totalPriceLocator = By.cssSelector(".cart-summary-line.cart-total span.value");
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(totalPriceLocator, "€22.94")
        ));

        WebElement totalPrice = driver.findElement(totalPriceLocator);
        String actualPrice = totalPrice.getText().replace("€", "").trim();
        System.out.println("Cena aktualna produktu wynosi:" + actualPrice);
        assertEquals("45.89", actualPrice);

        driver.quit();

    }

    @Test

    public void homePageCheckingTest() throws InterruptedException {

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

        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By productLocator =By.cssSelector(".h3 a[href*=\'hummingbird-printed-t-shirt\']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        Thread.sleep(1000);
        By myStore = By.cssSelector("#_desktop_logo a");
        WebElement myStoreButton = wait.until(ExpectedConditions.elementToBeClickable(myStore));
        myStoreButton.click();

        By cartLocator = By.cssSelector("#_desktop_cart");
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartLocator));
        cartButton.click();

        By productNameInCart = By.cssSelector(".product-line-info a");
        WebElement productInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInCart));
        assertEquals("Hummingbird printed t-shirt", productInCart.getText());

        driver.quit();

    }
    @Test
    public void refreshingPageTest() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);

        By productLocator = By.xpath("//article[@data-id-product='1']");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLokator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLokator));
        addToCart.click();

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        driver.navigate().refresh();
        driver.switchTo().frame("framelive");

        By cartProductLocator = By.cssSelector(".cart-products-count");
        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductLocator));
        assertEquals("(1)", cartCount.getText());

        driver.quit();

    }

}