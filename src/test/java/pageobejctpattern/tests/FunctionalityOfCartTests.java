package pageobejctpattern.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobejctpattern.tests.pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalityOfCartTests{

    ChromeDriver driver;
    WebDriverWait wait;
    private ProductGrid productGrid;
    private Filters filters;
    private ActiveFiltres activeFiltres;
    private Navigation navigation;
    private Cart cart;


    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        Duration timeToWait = Duration.ofSeconds(30);
        driver.manage().timeouts().implicitlyWait(timeToWait);
        driver.get("https://demo.prestashop.com/#/en/front");
        By iframe = By.id("framelive");
        WebElement iframeObject = driver.findElement(iframe);
        driver.switchTo().frame(iframeObject);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By spinnerLocator = By.id("loadingMessage");
        ExpectedCondition elementInvisible = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator);
        wait.until(elementInvisible);
    }

    @Test
    public void addingProductToCartTest() {
        productGrid = new ProductGrid(driver);
        productGrid.selectProduct();
        cart = new Cart(driver);

        cart.addingProductToCart();
        cart.confirmationAfterAddingToCart();

        String expectedText = "Product successfully added to your shopping cart";
        assertTrue(cart.confirmationAfterAddingToCart().contains(expectedText), "Text not found");
    }

    @Test
    public void quantityOfProductTest() {

        productGrid = new ProductGrid(driver);
        cart = new Cart(driver);

        productGrid.clickOnAllProductsButton();
        productGrid.selectTshirt();
        cart.addingProductToCart();
        cart.proceedToCheckoutButton();
        cart.getItemCount();
        int expectedCount = 1;

        assertEquals(expectedCount, cart.getItemCount(), "Liczba w koszyku jest niezgodna z oczekiwaną");
    }

    @Test
    public void removingTheProductTest() {

    productGrid = new ProductGrid(driver);
    cart = new Cart(driver);

    productGrid.clickOnAllProductsButton();
    productGrid.selectTshirt();
    cart.addingProductToCart();
    cart.proceedToCheckoutButton();
    cart.removingProduct();
    cart.confirmationOfProductRemoval();

        assertEquals("There are no more items in your cart", cart.confirmationOfProductRemoval().getText().trim());
    }

    @Test
    public void proceedToCheckoutTest() {

        productGrid = new ProductGrid(driver);
        cart = new Cart(driver);

        productGrid.clickOnAllProductsButton();
        productGrid.selectTshirt();
        cart.addingProductToCart();
        cart.proceedToCheckoutButton();
        cart.secondProceedToCheckout();
        cart.checkoutForm();

        Assertions.assertTrue(cart.checkoutForm().isDisplayed());
    }

    @Test
    public void priseCheckingTest() {

        productGrid = new ProductGrid(driver);
        cart = new Cart(driver);

        productGrid.clickOnAllProductsButton();
        productGrid.selectTshirt();
        cart.addingProductToCart();
        cart.proceedToCheckoutButton();
        cart.increasingTheQuantityOfProduct();
        cart.productPrice();

        assertEquals("45.89", cart.productPrice());

    }

    @Test

    public void homePageCheckingTest() throws InterruptedException {

        productGrid = new ProductGrid(driver);
        cart = new Cart(driver);

        productGrid.clickOnAllProductsButton();
        productGrid.selectTshirt();
        cart.addingProductToCart();
        cart.proceedToCheckoutButton();
        productGrid.comingBackToMainPage();
        cart.entryToCart();
        cart.productInCart();

        assertEquals("Hummingbird printed t-shirt", cart.productInCart().getText());

    }
    @Test
    public void refreshingPageTest() {

        productGrid = new ProductGrid(driver);
        cart = new Cart(driver);
        navigation= new Navigation(driver);

        productGrid.selectProduct();
        cart.addingProductToCart();
        cart.proceedToCheckoutButton();
        navigation.refreshingPage();
        cart.productCountInCart();

        assertEquals("(0)", cart.productCountInCart().getText());

    }
    @AfterEach
    public void afterEach(){
        driver.quit();
    }
}