package qa.project.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResult {
    public static SelenideElement productIphone = $("li:nth-child(1) app-buy-button");
    public static ElementsCollection appleCategory = $$("rz-list-tile > div");
    public static SelenideElement clickFirstAppleCategory = $("rz-widget-list:nth-child(2)");
    public static SelenideElement title = $("h1");
    public static SelenideElement titleTotalProducts = $("div:nth-child(1) > div > rz-selected-filters > div > p");

    public static SelenideElement productTabSize = $("rz-grid > ul > li:nth-child(1) > rz-catalog-tile");
    public static SelenideElement preloader = $(".preloader_type_element");
    public static SelenideElement firstProduct = $("li:nth-child(1) div.goods-tile__prices span.goods-tile__price-value");
    public static SelenideElement secondProduct = $("li:nth-child(3) div.goods-tile__prices span.goods-tile__price-value");

    public static void iphoneProductClick() {
        productIphone.click();
    }
    public static void appleCategorySize(int count) {
        appleCategory.shouldHave(CollectionCondition.size(count));
    }
    public static void clickFirstAppleCategory() {
        clickFirstAppleCategory.click();
    }
    public static void titleName(String value) {
        title.shouldHave(partialText(value));
    }
    public static void tabSizeHeight() {
        productTabSize.getSize().getHeight();
    }
    public static void tabSizeWidth() {
        productTabSize.getSize().getWidth();
    }

}
