package lesson12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FunctionalityOfCart {

    @Test
    public void addingProductToCart() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

        Assertions.assertTrue(confirmationText.contains(expectedText), "Text not found");
    }


    @Test
    public void quanityOfProduct() {

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By productLocator = By.xpath("//h2[@class=\"h3 product-title\"]/a[contains(text(), \"Hummingbird printed t-shirt\")]");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By quantitiyLocator = By.xpath("//input[@class=\"js-cart-line-product-quantity form-control\"]");
        WebElement cartItemCountElement = driver.findElement(quantitiyLocator);

        String itemCountText = cartItemCountElement.getAttribute("value");
        int itemCount = Integer.parseInt(itemCountText.trim());

        int expectedCount = 1;

        Assertions.assertEquals(expectedCount, itemCount, "Liczba w koszyku jest niezgodna z oczekiwaną");
    }

    @Test
    public void removingTheProduct(){

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By productLocator = By.xpath("//h2[@class=\"h3 product-title\"]/a[contains(text(), \"Hummingbird printed t-shirt\")]");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By removingLocator = By.cssSelector(".cart-line-product-actions .remove-from-cart");
        WebElement removingButton = wait.until(ExpectedConditions.elementToBeClickable(removingLocator));
        removingButton.click();

        By cartInformationText = By.cssSelector(".cart-detailed-totals div.cart-summary-line#cart-subtotal-products");
        WebElement confirmationPriseMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(cartInformationText));
        String confirmationText = confirmationPriseMessage.getText().trim();
//        confirmationText= confirmationText.replace("€", "").trim();
       String expectedMessage="0 items";


        Assertions.assertEquals(expectedMessage,confirmationText,"There is something wrong");
    }


    @Test
    public void proceedToCheckout(){

        ChromeDriver driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(40);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By allProductsLocator = By.cssSelector(".all-product-link");
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));
        allProductButton.click();

        By productLocator = By.xpath("//h2[@class=\"h3 product-title\"]/a[contains(text(), \"Hummingbird printed t-shirt\")]");
        WebElement product1Choice = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product1Choice.click();

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

        By proceedToCheckoutLocator = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();

        By proceedToCheckoutLocator2 = By.cssSelector("div.checkout  div.text-sm-center");
        WebElement proceedToCheckoutButton2 = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator2));
        proceedToCheckoutButton2.click();

        WebElement information = driver.findElement()








    }

}





