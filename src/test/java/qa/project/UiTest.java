package qa.project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.pages.BasePage;
import qa.project.pages.CartModal;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;


public class UiTest {

    @BeforeMethod
    public void beforeMethod() {
        open("https://rozetka.com.ua/");
    }

    @Test
    public void Task1() {
        BasePage.cart.shouldNotBe(Condition.attribute("header__button--active"));
        BasePage.search.setValue("iphone").pressEnter();
        $("li:nth-child(1) app-buy-button").click();
        $(".badge.badge--green.ng-star-inserted").shouldHave(Condition.text("1"));
        BasePage.cart.click();
        CartModal.cartModal.shouldHave(CollectionCondition.size(1));
        $("#cartProductActions0").doubleClick();
        CartModal.cartModal.shouldHave(CollectionCondition.size(0));
    }

    @Test
    public void Task2() {
        BasePage.search.setValue("Apple");
        $("div form > button").click();
        $$("rz-list-tile > div").shouldHave(CollectionCondition.size(20));
        $("rz-widget-list:nth-child(2)").click();
        $("h1").shouldHave(partialText("Apple"));
    }

    @Test
    public void Task3() {
        BasePage.search.setValue("iphone 13").pressEnter();
        $("rz-selected-filters > div > ul > li:nth-child(3)").shouldHave(Condition.text("iPhone 13"));
        int firstResult = Integer.parseInt($("rz-catalog-settings > div > rz-selected-filters > div > p").getText().replaceAll("\\D+", ""));
        sleep(5000);
        $("rz-filter-stack > div:nth-child(1)").click();
        int secondResult = Integer.parseInt($("rz-catalog-settings > div > rz-selected-filters > div > p").getText().replaceAll("\\D+", ""));
        assertTrue(firstResult == secondResult, "firstResult is not = then secondResult");

    }

    @Test
    public void Task4() {
        BasePage.search.setValue("iphone 13").pressEnter();
        $("rz-grid > ul > li:nth-child(1) > rz-catalog-tile").getSize().getHeight();
        $("rz-grid > ul > li:nth-child(1) > rz-catalog-tile").getSize().getWidth();
        $("rz-view-switch > div > button:nth-child(1)").click();
        $("rz-grid > ul > li:nth-child(1) > rz-catalog-tile").getSize().getHeight();
        $("rz-grid > ul > li:nth-child(1) > rz-catalog-tile").getSize().getWidth();
    }

    @Test
    public void Task5() {
        BasePage.search.setValue("iphone").pressEnter();
        $("rz-catalog-settings > div > rz-sort > select").selectOption("Від дорогих до дешевих");
        sleep(5000);
        int firstNumber = Integer.parseInt($("li:nth-child(1) div.goods-tile__prices span.goods-tile__price-value").getText().replaceAll("\\D+", ""));
        int secondNumber = Integer.parseInt($("li:nth-child(3) div.goods-tile__prices span.goods-tile__price-value").getText().replaceAll("\\D+", ""));
        assertTrue(firstNumber > secondNumber, "firstNumber is not > then secondNumber");
    }
}

