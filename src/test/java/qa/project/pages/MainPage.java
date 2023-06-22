package qa.project.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public static SelenideElement cart = $(".header-actions__item--cart");

    public static SelenideElement search = $("[name='search']");
    public static SelenideElement cartGreenBadge = $(".badge.badge--green.ng-star-inserted");

    public static SelenideElement searchButton = $("div form > button");

}