package qa.project.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartModal {
    public static ElementsCollection cartModal = $$("li.cart-list__item");

    public static SelenideElement cartProductAction = $("#cartProductActions0");

    public static void productsInCart(int count) {
        cartModal.shouldHave(CollectionCondition.size(count));
    }
    public static void deleteProductFromCart() {
        cartProductAction.doubleClick();
    }
}
