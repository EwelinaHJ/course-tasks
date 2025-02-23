package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {
    private final WebDriverWait wait;

    public Cart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    WebDriver driver;


    public void addingProductToCart(){

        By addToCartLocator = By.cssSelector(".product-add-to-cart");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCart.click();

    }
    public String confirmationAfterAddingToCart(){

        By confirmationTextLocator = By.cssSelector("#myModalLabel");
        WebElement confirmationTextElement = wait.until(ExpectedConditions.elementToBeClickable(confirmationTextLocator));
        String confirmationText = confirmationTextElement.getText();
        return confirmationText;
    }

    public void proceedToCheckoutButton (){

        By proceedToCheckoutLocator = By.cssSelector(".cart-content a");
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator));
        proceedToCheckoutButton.click();
    }
    public int getItemCount() {
        WebElement cartItemCountElement = driver.findElement(By.cssSelector(".input-group input"));
        String itemCountText = cartItemCountElement.getAttribute("value");
        return Integer.parseInt(itemCountText.trim());
    }

    public void removingProduct(){

        By removingLocator = By.cssSelector(".cart-line-product-actions .remove-from-cart");
        WebElement removingButton = wait.until(ExpectedConditions.elementToBeClickable(removingLocator));
        removingButton.click();
    }

    public WebElement confirmationOfProductRemoval(){

        By emptyCartMessage = By.cssSelector(".cart-overview.js-cart .no-items");
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        return message;
    }

    public void secondProceedToCheckout(){

        By proceedToCheckoutLocator2 = By.cssSelector("div.checkout  div.text-sm-center");
        WebElement proceedToCheckoutButton2 = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutLocator2));
        proceedToCheckoutButton2.click();
    }

    public WebElement checkoutForm(){

        By checkoutFormLocator = By.id("checkout-personal-information-step");
        WebElement checkoutForm = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutFormLocator));
        return checkoutForm;
    }

    public void increasingTheQuantityOfProduct(){

        By addingProduct = By.cssSelector(".js-increase-product-quantity");
        WebElement upArrowButton = wait.until(ExpectedConditions.elementToBeClickable(addingProduct));
        upArrowButton.click();
    }

    public String productPrice(){

        By totalPriceLocator = By.cssSelector(".cart-summary-line.cart-total span.value");
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(totalPriceLocator, "€22.94")
        ));

        WebElement totalPrice = driver.findElement(totalPriceLocator);
        String actualPrice = totalPrice.getText().replace("€", "").trim();
        return actualPrice;

    }

    public void entryToCart(){

        By cartLocator = By.cssSelector("#_desktop_cart");
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartLocator));
        cartButton.click();

    }
    public WebElement productInCart(){

        By productNameInCart = By.cssSelector(".product-line-info a");
        WebElement productInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInCart));
        return productInCart;
    }

    public WebElement productCountInCart(){

        By cartProductLocator = By.cssSelector(".cart-products-count");
        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductLocator));
        return  cartCount;
    }
}


