package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalityOfCartTests {


    @Test
    public void addingProductToCartTest() {
        Configuration.timeout = 20000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame("framelive");
        $("#loadingMessage").shouldBe(hidden);
        $x("//article[@data-id-product='1']").shouldBe(visible).click();

        $(".add-to-cart").shouldBe(visible).click();

        $("#myModalLabel").shouldBe(visible)
                .shouldHave(text("Product successfully added to your shopping cart"));
    }

    @Test
    public void quanityOfProductTest() {

        Configuration.timeout = 20000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame("framelive");

        $("#loadingMessage").shouldBe(hidden);

        $(".all-product-link").shouldBe(clickable).click();

        $(".h3 a[href*='hummingbird-printed-t-shirt']").shouldBe(clickable).click();

        $(".product-add-to-cart").shouldBe(clickable).click();

        $(".shopping-cart").shouldBe(visible);

        int itemCount = Integer.parseInt($(".input-group input").getValue().trim());

        assertEquals(1, itemCount, "Liczba w koszyku jest niezgodna z oczekiwaną");
    }

    @Test
    public void removingTheProductTest() {
        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");

        switchTo().frame($("#framelive"));

        $("#loadingMessage").shouldBe(hidden);
        $(".all-product-link").shouldBe(clickable).click();
        $(".h3 a[href*='hummingbird-printed-t-shirt']").shouldBe(clickable).click();
        sleep(2000);
        $("button.add-to-cart").shouldBe(visible, clickable).click();
        sleep(2000);
        $(".cart-content a").shouldBe(visible).click();
        $(".cart-line-product-actions .remove-from-cart").shouldBe(clickable).click();
        $(".cart-overview.js-cart .no-items").shouldBe(visible)
                .shouldHave(text("There are no more items in your cart"));
    }

    @Test
    public void proceedToCheckoutTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");

        switchTo().frame($("#framelive"));

        $("#loadingMessage").shouldBe(hidden);
        $(".all-product-link").shouldBe(clickable).click();
        $(".h3 a[href*='hummingbird-printed-t-shirt']").shouldBe(clickable).click();
        sleep(2000);
        $("button.add-to-cart").shouldBe(visible, clickable).click();
        sleep(2000);
        $(".cart-content a").shouldBe(visible).click();
        $("div.checkout div.text-sm-center").click();
        assertTrue($("#checkout-personal-information-step").getWrappedElement().isDisplayed());
    }


    @Test

    public void homePageCheckingTest() throws InterruptedException {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");


        switchTo().frame($("#framelive"));


        $("#loadingMessage").shouldBe(hidden);

        $(".all-product-link").shouldBe(clickable).click();

        $(".h3 a[href*='hummingbird-printed-t-shirt']").shouldBe(clickable).click();

        sleep(2000);
        $("button.add-to-cart").shouldBe(visible, clickable).click();

        $(".cart-content a").shouldBe(visible, clickable).click();

        sleep(1000);
        $("#_desktop_logo a").shouldBe(clickable).click();

        $("#_desktop_cart").shouldBe(clickable).click();

        $(".product-line-info a").shouldBe(visible)
                .shouldHave(text("Hummingbird printed t-shirt"));

        assertEquals("Hummingbird printed t-shirt",
                $(".product-line-info a").shouldBe(visible).getText());
    }

    @Test
    public void refreshingPageTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");

        switchTo().frame($("#framelive"));

        $("#loadingMessage").shouldBe(hidden);

        $x("//article[@data-id-product='1']").shouldBe(clickable).click();

        sleep(2000);
        $("button.add-to-cart").shouldBe(visible, clickable).click();

        $(".cart-content a").shouldBe(visible, clickable).click();

        refresh();
        switchTo().frame("framelive");

        $(".cart-products-count").shouldBe(visible)
                .shouldHave(text("(0)"));
        assertEquals("(0)", $(".cart-products-count").shouldBe(visible).getText());
    }

}