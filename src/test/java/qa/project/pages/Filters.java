package qa.project.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Filters {
    public static SelenideElement filterXiaomi = $("rz-selected-filters > div > ul > li:nth-child(2)");
    public static SelenideElement sellerRozetka = $("div:nth-child(2) > div > rz-scrollbar > div > div.scrollbar__inner > div > div > rz-filter-checkbox > ul:nth-child(1) > li:nth-child(1) > a");
    public static SelenideElement gridView = $("rz-view-switch > div > button:nth-child(1)");
    public static SelenideElement expensiveToCheap = $("rz-catalog-settings > div > rz-sort > select");
}
