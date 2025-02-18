package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

public class UserFunctionsTests {

    @Test
    public void newUserRegistrationTest() {

        Configuration.timeout = 40000;
        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);
        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();
        $(".no-account").should(visible, Condition.enabled).click();

        String email = "test" + System.currentTimeMillis() + "@test.com";

        $x("//label[@class='radio-inline'][@for='field-id_gender-2']").click();
        $("#field-firstname").setValue("Ewelina");
        $("#field-lastname").setValue("Nowacka");
        $("#field-email").setValue(email);
        $("#field-password").setValue("EwelinkaNowacka78460!");
        $("#field-birthday").setValue("09/09/1990");

        $x("//input[@name='optin']").click();
        $x("//input[@name='psgdpr']").click();
        $x("//input[@name='newsletter']").click();
        $x("//input[@name='customer_privacy']").click();

        $x("//button[@type='submit']").click();

        $x("//span[@class='hidden-sm-down'][contains(text(), 'Ewelina Nowacka')]")
                .as("Nazwa zalogowanego użytkownika")
                .shouldBe(visible)
                .shouldHave(
                        exactText("Ewelina Nowacka")
                );
    }

    @Test
    public void loginCorrectDataTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));

        $("#loadingMessage").should(Condition.disappear);
        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();
        String email = "test" + System.currentTimeMillis() + "@test.com";

        $("#field-email").setValue(email);
        $("#field-password").should(visible, Condition.enabled)
                .setValue("EwelinkaNowacka78460!");
        $("#submit-login").should(visible, Condition.enabled).click();
    }

    @Test
    public void fieldlessRegistrationTest() {

        Configuration.timeout = 40000;
        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);

        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();
        $(".no-account").should(visible, Condition.enabled).click();

        $x("//label[@class='radio-inline'][@for='field-id_gender-2']").click();
        $("#field-firstname").setValue("Ewelina");
        $("#field-lastname").setValue("3456789");
        $("#field-email").setValue("Ewelink2024@gmail.com");
        $("#field-password").setValue("EwelinkaNowacka78460!");
        $("#field-birthday").setValue("ZZ/09/n99w");

        $x("//input[@name='optin']").click();
        $x("//input[@name='psgdpr']").click();
        $x("//input[@name='newsletter']").click();
        $x("//input[@name='customer_privacy']").click();

        $x("//button[@type='submit']").click();

        $x("//li[@class='alert alert-danger'][contains(text(),'Invalid format')]")
                .as("Komunikat błędu dla nazwiska")
                .shouldBe(visible)
                .shouldHave(
                        exactText("Invalid format.")
                );

        $x("//li[@class='alert alert-danger'][contains(text(),'Format should be 05/31/1970')]")
                .as("Komunikat błędu dla daty urodzenia")
                .shouldBe(visible)
                .shouldHave(
                        exactText("Format should be 05/31/1970.")
                );
    }

    @Test
    public void loginIncorrectDataTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);

        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();

        $("#field-email").setValue("Ewelinka2024@gmail.com");
        $("#field-password").should(visible, Condition.enabled)
                .setValue("EwelinkaNowacka7846xd!");

        $("#submit-login").should(visible, Condition.enabled).click();

       $x("//li[@class='alert alert-danger'][contains(text(),'Authentication failed')]")
        .shouldBe(visible)
        .shouldHave(
         exactText("Authentication failed."));
    }

    @Test
    public void passwordResetTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);

        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();
        $(".forgot-password").click();

        $x("//input[@type='email'][@name='email'][@id='email']")
                .should(visible)
                .setValue("Ewelinka2024@gmail.com");

        $("#send-reset-link").click();

        $(".ps-alert-success")
                .shouldBe(visible)
                .shouldHave(
                        text("If this email address has been registered in our store"),
                        text("Ewelinka2024@gmail.com")
                );

        $(".ps-alert-success")
                .shouldBe(visible)
                .shouldHave(
                        text("If this email address has been registered in our store"),
                        text("Ewelinka2024@gmail.com")
                );
    }

    @Test

    public void loginWithoutCompletedFieldsTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));

        $("#loadingMessage").should(Condition.disappear);
        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]").click();

        $("#submit-login").should(Condition.enabled).click();
        $("#field-email").shouldHave(attribute("required", "true"));
        $("#field-password").shouldHave(attribute("required", "true"));
        assertEquals("true", $("#field-email").getAttribute("required"));
        assertEquals("true", $("#field-password").getAttribute("required"));
    }

    @Test
    public void checkIfPasswordIsMaskedTest() {

        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);
        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]")
                .should(Condition.enabled)
                .click();
        $("#field-password").should(Condition.exist);

        $("#field-password").shouldHave(attribute("type", "password"));
        $("#field-password").setValue("TestPassword123!");
        $("#field-password").shouldHave(value("TestPassword123!"));
    }


    @Test
    public void checkShowPasswordButtonTest() {
        Configuration.timeout = 40000;

        open("https://demo.prestashop.com/#/en/front");
        switchTo().frame($("#framelive"));
        $("#loadingMessage").should(Condition.disappear);
        $x("//span[@class='hidden-sm-down'][contains(text(), 'Sign in')]")
                .should(Condition.enabled)
                .click();
        $("#field-password").setValue("TestPassword123!");
        $x("//span[@class='input-group-btn']/button")
                .should(Condition.enabled)
                .click();
        $("#field-password")
                .shouldHave(attribute("type", "text"));
    }
}