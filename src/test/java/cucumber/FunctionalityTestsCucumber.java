package cucumber;

import cucumber.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;


    public class FunctionalityTestsCucumber {

        ChromeDriver driver = new ChromeDriver();
        private SetUp setUp = new SetUp(driver);
        private ProductGrid productGrid = new ProductGrid(driver);
        private ActiveFiltres activeFiltres = new ActiveFiltres(driver);
        private Filters filters = new Filters(driver);


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

    }


