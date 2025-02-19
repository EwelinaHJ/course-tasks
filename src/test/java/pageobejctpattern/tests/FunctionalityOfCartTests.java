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
    private SetUp setUp;

    public FunctionalityOfCartTests() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.productGrid = new ProductGrid(driver);
        this.filters = new Filters(driver);
        this.activeFiltres = new ActiveFiltres(driver);
        this.navigation = new Navigation(driver);
        this.cart = new Cart(driver);
        this.setUp = new SetUp(driver);
    }

    @BeforeEach
    public void beforeEach() {
        setUp.settings();
    }

    @Test
    public void addingProductToCartTest() {

        productGrid.selectProduct();
        cart.addingProductToCart();
        cart.confirmationAfterAddingToCart();
        String expectedText = "Product successfully added to your shopping cart";
        assertTrue(cart.confirmationAfterAddingToCart().contains(expectedText), "Text not found");
    }

    @Test
    public void quantityOfProductTest() {

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