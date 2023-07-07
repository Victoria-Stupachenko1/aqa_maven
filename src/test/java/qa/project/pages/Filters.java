package qa.project.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Filters {
    public static ElementsCollection filterCategoryProduct = $$("rz-selected-filters > div > ul > li");
    public static SelenideElement sellerRozetka = $("div:nth-child(2) > div > rz-scrollbar > div > div.scrollbar__inner > div > div > rz-filter-checkbox > ul:nth-child(1) > li:nth-child(1) > a");
    public static SelenideElement gridView = $("rz-view-switch > div > button:nth-child(1)");
    public static SelenideElement expensiveToCheap = $("rz-catalog-settings > div > rz-sort > select");

    public static void categoryProductFilter(String value) {
        filterCategoryProduct.filter(Condition.text(value)).shouldHave(CollectionCondition.size(1));
    }

    public static void clickRozetkaSeller() {
        sellerRozetka.click();
    }

    public static void gridViewClick() {
        gridView.click();
    }

    public static void relevanceFilterClick() {
        expensiveToCheap.click();
    }

    public static void relevanceFilterOptionChoose(String value) {
        expensiveToCheap.selectOption(value);
    }

}
