package qa.project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.pages.Filters;
import qa.project.pages.MainPage;
import qa.project.pages.CartModal;
import qa.project.pages.SearchResult;

import java.time.Duration;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;


public class UiTest {

    @BeforeMethod (description = "Open main page")
    public void beforeMethod() {
        open("https://rozetka.com.ua/ua/");
    }

    @Test(description = "Add and delete product from a cart")
    public void Task1() {
        MainPage.cart.shouldNotBe(Condition.attribute("header__button--active"));
        MainPage.search("iphone");
        SearchResult.productIphone.click();
        MainPage.cartGreenBadge.shouldHave(Condition.text("1"));
        MainPage.cart.click();
        CartModal.cartModal.shouldHave(CollectionCondition.size(1));
        CartModal.cartProductAction.doubleClick();
        CartModal.cartModal.shouldHave(CollectionCondition.size(0));
    }

    @Test(description = "Check categories for 'Apple' search")
    public void Task2() {
        MainPage.search("Apple");
        MainPage.searchButton.click();
        SearchResult.appleCategory.shouldHave(CollectionCondition.size(20));
        SearchResult.clickFirstAppleCategory.click();
        SearchResult.title.shouldHave(partialText("Apple"));
    }

    @Test(description = "Check filters 'iphone 13' and reseller 'Rozetka'")
    public void Task3() {
        MainPage.search("Xiaomi");
        Filters.filterXiaomi.shouldHave(Condition.text("Xiaomi"));
        int firstResult = Integer.parseInt(SearchResult.titleTotalProducts.getText().replaceAll("\\D+", ""));
        SearchResult.preloader.shouldNotBe(Condition.visible, Duration.ofSeconds(10000));
        Filters.sellerRozetka.click();
        int secondResult = Integer.parseInt(SearchResult.titleTotalProducts.getText().replaceAll("\\D+", ""));
        assertTrue(firstResult > secondResult, "firstResult is = secondResult");

    }

    @Test(description = "Assert size of product tab")
    public void Task4() {
        MainPage.search("iphone 13");
        SearchResult.productTabSize.getSize().getHeight();
        SearchResult.productTabSize.getSize().getWidth();
        Filters.gridView.click();
        SearchResult.productTabSize.getSize().getHeight();
        SearchResult.productTabSize.getSize().getWidth();
    }

    @Test(description = "Check fiter 'from higher prices to lower’")
    public void Task5() {
        MainPage.search("iphone");
        Filters.expensiveToCheap.click();
        Filters.expensiveToCheap.selectOption("Від дорогих до дешевих");
        SearchResult.preloader.shouldNotBe(Condition.visible, Duration.ofSeconds(10000));
        int firstNumber = Integer.parseInt(SearchResult.firstProduct.getText().replaceAll("\\D+", ""));
        int secondNumber = Integer.parseInt(SearchResult.secondProduct.getText().replaceAll("\\D+", ""));
        assertTrue(firstNumber > secondNumber, "firstNumber is not > then secondNumber");
    }
}

