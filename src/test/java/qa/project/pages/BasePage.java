package qa.project.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
public class BasePage {
    public static SelenideElement cart = $(".header-actions__item--cart");

    public static SelenideElement search = $("[name='search']");
}