import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.ProductPage;
import pages.SearchResultPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class FirstTest {

    @BeforeEach
    void setUp() {

//        System.setProperty("webdriver.chrome.driver", )
//        Configuration.browser = "firefox";
//        Configuration.headless = true;
        Configuration.baseUrl = "https://www.foxtrot.com.ua/uk";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void firstTest() {
        open("");
        $(".header-search__field").setValue("iphone").pressEnter();
//        webdriver().shouldHave(urlContaining("mobilnye_telefony_apple.html"));
        String productName = $(".listing__body .card__title").getText();
        $(".listing__body-wrap .card__image").shouldBe(Condition.visible).click();
        $(".page__title.overflow").shouldHave(Condition.text(productName));
        $(".button.product-box__main-buy__button").shouldBe(Condition.visible).click();
        $(".header-tooltip__cards-item__title").shouldHave(Condition.text(productName));


    }

    @Test
    void secondTest() {
        open("");
        $(BasePage.searchField).setValue("iphone").pressEnter();
        webdriver().shouldHave(urlContaining("mobilnye_telefony_apple.html"));
        String productName = $(SearchResultPage.productName).getText();
        $(SearchResultPage.productImage).shouldBe(Condition.visible).click();
        $(ProductPage.productName).shouldHave(Condition.text(productName));
        $(ProductPage.addToCartButton).shouldBe(Condition.visible).click();
        $(BasePage.productNameInMiniCart).shouldHave(Condition.text(productName));

    }
}





