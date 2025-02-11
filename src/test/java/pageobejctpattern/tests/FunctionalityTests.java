package pageobejctpattern.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobejctpattern.tests.pages.ActiveFiltres;
import pageobejctpattern.tests.pages.Filters;
import pageobejctpattern.tests.pages.Navigation;
import pageobejctpattern.tests.pages.ProductGrid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class FunctionalityTests {
    ChromeDriver driver;
    WebDriverWait wait;
    private ProductGrid productGrid;
    private Filters filters;
    private ActiveFiltres activeFiltres;
    private Navigation navigation;


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
    public void filteringByCategoryTest() {

        productGrid = new ProductGrid(driver);
        filters = new Filters(driver);
        activeFiltres = new ActiveFiltres(driver);

        productGrid.clickOnAllProductsButton();
        filters.selectAccessoriesFIlter();

        String actualText = activeFiltres.getActiveFilter();
        String expectedText = "Categories: Accessories";
        Assertions.assertTrue(actualText.contains(expectedText), "Text not found");
    }

    @Test
    public void filteringByPriceTest() {
        productGrid = new ProductGrid(driver);
        filters = new Filters(driver);
        activeFiltres = new ActiveFiltres(driver);

        productGrid.clickOnAllProductsButton();
        filters.selectPriceFilter();
        activeFiltres.getFilteredProductsByPrice();

        List<WebElement> allProducts = activeFiltres.getAllProducts();

        Assertions.assertEquals(10, allProducts.size(), "Nieprawidłowa liczba produktów po filtrowaniu");

    }

    @Test
    public void filterResetTest() {

        productGrid = new ProductGrid(driver);
        filters = new Filters(driver);
        activeFiltres = new ActiveFiltres(driver);
        navigation = new Navigation(driver);

        productGrid.clickOnAllProductsButton();
        int sumOfAllProducts = productGrid.totalProductsCounter();

        int sumOfProductsFromFirstPage= productGrid.productsOn1Page();

        navigation.goingToSecondPage();
        productGrid.productsOn2Page();
        int sumOfProductsFromSecondPage = productGrid.productsOn2Page();

        int totalInitialCount = sumOfProductsFromSecondPage + sumOfProductsFromFirstPage;
        filters.selectFilterAccessories();
        int sumOfProductsAfterApplyingFilters = productGrid.amountOfFilteredProducts();
        navigation.reset();

        Assertions.assertTrue(sumOfProductsAfterApplyingFilters < sumOfAllProducts,
                "Liczba produktów po filtrowaniu powinna być mniejsza niż początkowa");
        Assertions.assertEquals(sumOfAllProducts, productGrid.productsAfterReset(),
                "Liczba produktów po resecie powinna być równa liczbie z licznika");

    }
        @Test
    public void checkTwoFiltersVisibilityTest() {

        productGrid = new ProductGrid(driver);
        filters = new Filters(driver);
        activeFiltres = new ActiveFiltres(driver);

        productGrid.clickOnAllProductsButton();
        filters.selectFilterAccessories();
        activeFiltres.isAccessoriesFilterVisible();

        Assertions.assertTrue(activeFiltres.isAccessoriesFilterVisible(),"Filtr Accessories is visible");

        activeFiltres.clickOnRuledFilter();
        activeFiltres.isRuledFilterVisible();

        Assertions.assertTrue(activeFiltres.isRuledFilterVisible(),"Filtr Ruled is visible");
    }

    @AfterEach
    public void afterEach(){

        driver.quit();
    }
}