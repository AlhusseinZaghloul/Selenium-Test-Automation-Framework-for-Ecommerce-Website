package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementsActions;

public class CartPage {
    private final WebDriver driver;
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".btn_action.checkout_button");

    // dynamic XPath template: given a product name, find its price in the same cart_item
    private static final String PRICE_BY_NAME_XPATH =
            "//div[@class='inventory_item_name' and text()=\"%s\"]" +
                    "/ancestor::div[contains(@class,'cart_item')]" +
                    "//div[@class='inventory_item_price']";

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get product name")
    public String getProductName() {
        return ElementsActions.getText(driver, productName);
    }

    @Step("Get product price")
    public String getProductPrice() {
        // if we only ever have one product on the page
        return ElementsActions.getText(driver, productPrice);
    }

    // grab price by visible product name
    @Step("Get product price by name")
    public String getProductPriceByName(String name) {
        By priceLocator = By.xpath(String.format(PRICE_BY_NAME_XPATH, name));
        return ElementsActions.getText(driver, priceLocator);
    }

    @Step("Click on Checkout button")
    public CheckoutInfoPage clickOnCheckoutButton() {
        ElementsActions.clicking(driver, checkoutButton);
        return new CheckoutInfoPage(driver);
    }
}
