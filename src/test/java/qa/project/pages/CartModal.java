package qa.project.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CartModal {
    public static ElementsCollection cartModal = $$("li.cart-list__item");
}
