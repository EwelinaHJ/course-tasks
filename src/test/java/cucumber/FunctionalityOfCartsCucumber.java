package cucumber;

import cucumber.pages.Cart;
import cucumber.pages.Navigation;
import cucumber.pages.ProductGrid;
import cucumber.pages.SetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalityOfCartsCucumber {

    ChromeDriver driver = new ChromeDriver();
    private SetUp setUp = new SetUp(driver);
    private ProductGrid productGrid = new ProductGrid(driver);
    private Cart cart = new Cart(driver);
    private Navigation navigation = new Navigation(driver);

    @Given("Page is open")
    public void pageIsOpen() {

        setUp.settings();
    }
    @When("I select a product from the product list")
    public void iSelectAProductFromTheProductList() {
        productGrid.selectProduct();
    }
    @And("I click add to the cart button")
    public void iClickAddToTheCartButton() {
        cart.addingProductToCart();
    }
    @Then("I should see message {string} on the modal")
    public void iShouldSeeMessageOnTheModal(String arg0) {
        cart.confirmationAfterAddingToCart();
        String expectedText = "Product successfully added to your shopping cart";
        assertTrue(cart.confirmationAfterAddingToCart().contains(expectedText), "Text not found");
    }



    @When("I am on the product grid page")
    public void iAmOnTheProductGridPage() {
        productGrid.clickOnAllProductsButton();
    }
    @And("I select a T-shirt")
    public void iSelectATShirt() {
        productGrid.selectTshirt();
    }
    @And("I click add to the cart button and add product to the cart")
    public void iClickAddToTheCartButtonAndAddProductToTheCart() {
        cart.addingProductToCart();
    }
    @And("I proceed to checkout")
    public void iProceedToCheckout() {
        cart.proceedToCheckoutButton();
    }
    @Then("the cart should contain {int} item")
    public void theCartShouldContainItem(int arg0) {
        cart.getItemCount();
        int expectedCount = 1;
        assertEquals(expectedCount, cart.getItemCount(), "Liczba w koszyku jest niezgodna z oczekiwaną");
    }



    @And("I remove product from the cart")
    public void iRemoveProductFromTheCart() {
        cart.removingProduct();
    }
    @Then("I get confirmation  that the product has been removed from the cart")
    public void iGetConfirmationThatTheProductHasBeenRemovedFromTheCart() {
        cart.confirmationOfProductRemoval();
        assertEquals("There are no more items in your cart", cart.confirmationOfProductRemoval().getText().trim());
    }




    @And("I click second proceed to checkout button")
    public void iClickSecondProceedToCheckoutButton() {
        cart.secondProceedToCheckout();
    }
    @Then("the checkout form is displayed")
    public void theCheckoutFormIsDisplayed() {
        cart.checkoutForm();
        Assertions.assertTrue(cart.checkoutForm().isDisplayed());
    }




    @And("I increase the amount of the product")
    public void iIncreaseTheAmountOfTheProduct() {
        cart.increasingTheQuantityOfProduct();
    }
    @Then("I check whether the price has changed and is consistent")
    public void iCheckWhetherThePriceHasChangedAndIsConsistent() {
        cart.productPrice();
        assertEquals("45.89", cart.productPrice());
    }



    @And("I come back to the home page")
    public void iComeBackToTheHomePage() throws InterruptedException {
        productGrid.comingBackToMainPage();
    }
    @And("I entry again to the cart")
    public void iEntryAgainToTheCart() {
        cart.entryToCart();
    }
    @Then("I check if there is the same product in the basket")
    public void iCheckIfThereIsTheSameProductInTheBasket() {
        cart.productInCart();
        assertEquals("Hummingbird printed t-shirt", cart.productInCart().getText());
    }




    @When("I select one product")
    public void iSelectOneProduct() {
        productGrid.selectProduct();
    }
    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        cart.addingProductToCart();
    }
    @And("I press the proceed to checkout button")
    public void iPressTheProceedToCheckoutButton() {
        cart.proceedToCheckoutButton();
    }
    @And("I refresh the page")
    public void iRefreshThePage() {
        navigation.refreshingPage();
    }
    @Then("I check how many products are in the cart after refreshing the page")
    public void iCheckHowManyProductsAreInTheCartAfterRefreshingThePage() {
        cart.productCountInCart();
        assertEquals("(0)", cart.productCountInCart().getText());
    }
}