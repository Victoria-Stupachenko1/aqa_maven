package qa.project;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.pages.Filters;
import qa.project.pages.MainPage;
import qa.project.pages.CartModal;
import qa.project.pages.SearchResult;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;


public class UiTest {

    @BeforeMethod (description = "Open main page")
    public void beforeMethod() {
        open("https://rozetka.com.ua/ua/");
    }

    @Test(description = "Add and delete product from a cart")
    public void Task1() {
        MainPage.cartIconEmpty();
        MainPage.search("iphone");
        SearchResult.waitForSearchResultLoaded();
        SearchResult.iphoneProductClick();
        MainPage.checkProductInCart(1);
        MainPage.openCart();
        CartModal.productsInCart(1);
        CartModal.deleteProductFromCart();
        CartModal.productsInCart(0);
    }

    @Test(description = "Check categories for 'Apple' search")
    public void Task2() {
        MainPage.searchNoEnter("Apple");
        MainPage.searchButtonClick();
        SearchResult.waitForSearchResultLoaded();
        SearchResult.appleCategorySize(20);
        SearchResult.clickFirstAppleCategory();
        SearchResult.titleName("Apple");
    }

    @Test(description = "Check filters 'iphone 13' and reseller 'Rozetka'")
    public void Task3() {
        MainPage.search("Xiaomi");
        Filters.categoryProductFilter("Xiaomi");
        int firstResult = Integer.parseInt(SearchResult.titleTotalProducts.getText().replaceAll("\\D+", ""));
        SearchResult.preloader.shouldNotBe(Condition.visible, Duration.ofSeconds(10000));
        Filters.clickRozetkaSeller();
        int secondResult = Integer.parseInt(SearchResult.titleTotalProducts.getText().replaceAll("\\D+", ""));
        assertTrue(firstResult == secondResult, "firstResult is > secondResult");

    }

    @Test(description = "Assert size of product tab")
    public void Task4() {
        MainPage.search("iphone 13");
        int initialHeight = SearchResult.tabSizeHeight();
        int initialWidth = SearchResult.tabSizeWidth();
        Filters.gridViewClick();
        int gridHeight = SearchResult.tabSizeHeight();
        int gridWidth = SearchResult.tabSizeWidth();
        Assert.assertNotEquals(initialHeight, gridHeight, "Height should differ after changing to grid view");
        Assert.assertNotEquals(initialWidth, gridWidth, "Width should differ after changing to grid view");
    }

    @Test(description = "Check filter 'from higher prices to lower’")
    public void Task5() {
        MainPage.search("iphone");
        Filters.relevanceFilterClick();
        Filters.relevanceFilterOptionChoose("Від дорогих до дешевих");
        SearchResult.preloader.shouldNotBe(Condition.visible, Duration.ofSeconds(10000));
        int firstNumber = Integer.parseInt(SearchResult.firstProduct.getText().replaceAll("\\D+", ""));
        int secondNumber = Integer.parseInt(SearchResult.secondProduct.getText().replaceAll("\\D+", ""));
        assertTrue(firstNumber > secondNumber, "firstNumber is not > then secondNumber");
    }
}

