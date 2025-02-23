package cucumber;

import cucumber.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static cucumber.DriverProvider.getDriverInstance;


public class FunctionalityTestsCucumber {

    private SetUp setUp = new SetUp(getDriverInstance());
    private ProductGrid productGrid = new ProductGrid(getDriverInstance());
    private ActiveFiltres activeFiltres = new ActiveFiltres(getDriverInstance());
    private Filters filters = new Filters(getDriverInstance());
    private Navigation navigation = new Navigation(getDriverInstance());

    private int sumOfProductsAfterApplyingFilters;
    private int sumOfAllProducts;
    private int sumOfProductsFromFirstPage;



    @When("I will click the All Products button")
    public void iWillClickTheAllProductsButton() {
        productGrid.clickOnAllProductsButton();
    }
    @And("I will select the accessories filter and press it")
    public void iWillSelectTheAccessoriesFilterAndPressIt() {
        filters.selectAccessoriesFIlter();
    }
    @Then("I will see text confirming the filter has been applied")
    public void iWillSeeTextConfirmingTheFilterHasBeenApplied() {
        String actualText = activeFiltres.getActiveFilter();
        String expectedText = "Categories: Accessories";
        Assertions.assertTrue(actualText.contains(expectedText), "Text not found");
    }



    @And("I select the price filter and set the slider to a specific price range")
    public void iSelectThePriceFilterAndSetTheSliderToASpecificPriceRange() {
        filters.selectPriceFilter();
    }
    @Then("Products whose price is within the set price range are displayed")
    public void productsWhosePriceIsWithinTheSetPriceRangeAreDisplayed() {
        activeFiltres.getFilteredProductsByPrice();
        List<WebElement> allProducts = activeFiltres.getAllProducts();
        Assertions.assertEquals(10, allProducts.size(), "Nieprawidłowa liczba produktów po filtrowaniu");
    }



    @And("I get the total number of products")
    public void iGetTheTotalNumberOfProducts() {
        sumOfAllProducts = productGrid.totalProductsCounter();
    }
    @And("I count the products on the first page")
    public void iCountTheProductsOnTheFirstPage() {
        sumOfProductsFromFirstPage = productGrid.productsOn1Page();
    }
    @And("I'm going to the other page and count products")
    public void iMGoingToTheOtherPageAndCountProducts() {
        navigation.goingToSecondPage();
        int sumOfProductsFromSecondPage = productGrid.productsOn2Page();
        int totalInitialCount = sumOfProductsFromSecondPage + sumOfProductsFromFirstPage;
    }
    @And("I applies the accessories filter")
    public void iAppliesTheAccessoriesFilter() {
        filters.selectFilterAccessories();
        sumOfProductsAfterApplyingFilters = productGrid.amountOfFilteredProducts();
    }
    @Then("I press filter reset and count the products")
    public void iPressFilterResetAndCountTheProducts() {
        navigation.reset();
        Assertions.assertTrue(sumOfProductsAfterApplyingFilters < sumOfAllProducts,
                "Liczba produktów po filtrowaniu powinna być mniejsza niż początkowa");
        Assertions.assertEquals(sumOfAllProducts, productGrid.productsAfterReset(),
                "Liczba produktów po resecie powinna być równa liczbie z licznika");
    }



    @And("I check the filter application")
    public void iCheckTheFilterApplication() {
        Assertions.assertTrue(activeFiltres.isAccessoriesFilterVisible(), "Filtr Accessories is visible");
    }
    @And("I choose another filter: Ruled")
    public void iChooseAnotherFilterRuled() {
        activeFiltres.clickOnRuledFilter();
    }
    @Then("I check the second filter application")
    public void iCheckTheSecondFilterApplication() {
        Assertions.assertTrue(activeFiltres.isRuledFilterVisible(), "Filtr Ruled is visible");
    }
}