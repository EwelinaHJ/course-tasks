package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

public class FunctionalityTests {

    @Test
    public void filteringByCategoryTest() {
        Configuration.timeout = 25000;
        open("https://demo.prestashop.com/#/en/front");

        switchTo().frame($("#framelive"));

        $("#loadingMessage").shouldBe(hidden);

        $(".all-product-link").shouldBe(clickable).click();

        $x("//label[@class='facet-label']//a[contains(text(), \"Accessories\")]")
                .shouldBe(clickable).click();

        $(".filter-block")
                .as("Filtr kategorii")
                .shouldBe(visible)
                .shouldHave(
                        text("Categories: Accessories")
                );
    }

    @Test
    public void filteringByPriceTest() {
        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));

        $("#loadingMessage").shouldBe(hidden);
        $(".all-product-link").shouldBe(clickable).click();

        $(".faceted-slider").shouldBe(visible);
        sleep(1000);

        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement($(".ui-slider-handle:nth-of-type(1)").getWrappedElement())
                .clickAndHold()
                .moveByOffset(5, 0)
                .release()
                .perform();

        sleep(2000);
        $(".spinner").shouldBe(hidden);
        sleep(2000);

        int numberOfProducts = $$(".product").size();

        List<String> pricesInOrder = $$(".product").stream()
                .map(product -> product.$(".price").getText().replace("€", "").trim())
                .collect(Collectors.toList());

        assertTrue(numberOfProducts > 0, "Brak produktów po filtrowaniu");
        assertTrue(numberOfProducts <= 12, "Zbyt duża liczba produktów po filtrowaniu");
    }

    @Test
    public void filterResetTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);

        $(".all-product-link").should(Condition.visible, Condition.enabled).click();

        $(".total-products").should(Condition.visible);
        String totalProductsText = $(".total-products").getText();
        int expectedTotalProducts = Integer.parseInt(totalProductsText.replaceAll("[^0-9]", ""));

        $$(".product").shouldHave(sizeGreaterThan(0));
        int page1Count = $$(".product").size();

        $("li a[rel='next']").should(Condition.visible, Condition.enabled).click();
        $(".page-list .current").should(Condition.have(Condition.text("2")));

        $$(".product").shouldHave(sizeGreaterThan(0));
        int page2Count = $$(".product").size();

        int totalInitialCount = page1Count + page2Count;
        assertEquals(expectedTotalProducts, totalInitialCount,
                "Suma produktów z obu stron powinna być zgodna z licznikiem");

        $("li a[rel='prev']").should(Condition.visible, Condition.enabled).click();
        $(".page-list .current").should(Condition.have(Condition.text("1")));

        $x("//label[@class='facet-label']//a[contains(text(), 'Accessories')]")
                .should(Condition.visible, Condition.enabled).click();

        $(".total-products").shouldNot(Condition.have(Condition.text(totalProductsText)));
        $$(".product").shouldHave(sizeGreaterThan(0));
        int filteredProductCount = $$(".product").size();

        $("#_desktop_search_filters_clear_all button").should(Condition.visible, Condition.enabled).click();

        $(".total-products").should(Condition.have(Condition.text(totalProductsText)));
        $$(".product").shouldHave(sizeGreaterThan(0));
        int firstPageAfterReset = $$(".product").size();

        $("li a[rel='next']").should(Condition.visible, Condition.enabled).click();
        $(".page-list .current").should(Condition.have(Condition.text("2")));
        $$(".product").shouldHave(sizeGreaterThan(0));
        int secondPageAfterReset = $$(".product").size();

        int totalAfterReset = firstPageAfterReset + secondPageAfterReset;

        assertTrue(filteredProductCount < expectedTotalProducts,
                "Liczba produktów po filtrowaniu powinna być mniejsza niż początkowa");
        assertEquals(expectedTotalProducts, totalAfterReset,
                "Liczba produktów po resecie powinna być równa liczbie z licznika");
    }

    @Test
    public void checkTwoFiltersVisibilityTest() {
        Configuration.timeout = 25000;

        open("https://demo.prestashop.com/#/en/front");

        switchTo().frame($("#framelive"));

        $("#loadingMessage").should(Condition.disappear);
        $(".all-product-link").should(Condition.visible, Condition.enabled).click();
        $x("//label[@class='facet-label']//a[contains(text(), 'Accessories')]")
                .should(Condition.visible, Condition.enabled)
                .click();
        boolean isAccessoriesFilterVisible = $(".active-filter-title")
                .should(Condition.visible)
                .isDisplayed();

        $x("//label[@class='facet-label']//a[contains(text(), 'Ruled')]")
                .should(Condition.visible, Condition.enabled)
                .click();

        boolean isRuledFilterVisible = $(".active-filter-title")
                .should(Condition.visible)
                .isDisplayed();
    }
}