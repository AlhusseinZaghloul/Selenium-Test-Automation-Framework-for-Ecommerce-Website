package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import utils.BrowserActions;
import utils.ElementsActions;
import utils.LogsUtils;

public class HomePage {
    private final WebDriver driver;

    //Locators
    By cartIcon = By.id("shopping_cart_container");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open Home Page")
    public HomePage openHomePage(String url) {
        BrowserActions.navigateToUrl(driver, url);
        return this;
    }

    @Step("Add product to cart")
    public HomePage addProductToCart(String productName) {
        LogsUtils.info("Adding product to cart: " + productName);
        By addToCartButtonForProduct = RelativeLocator.with(By.tagName("button"))
                .below(By.xpath("//div[normalize-space()='" + productName + "']"));
        ElementsActions.clicking(driver, addToCartButtonForProduct);
        return this;
    }

    @Step("Click on cart icon")
    public CartPage clickOnCartIcon() {
        LogsUtils.info("Clicking on cart icon");
        ElementsActions.clicking(driver, cartIcon);
        return new CartPage(driver);
    }

    @Step("Get text of Add to Cart button")
    public String getTextOfAddToCartButton(String productName) {
        By addToCartButtonForProduct = RelativeLocator.with(By.tagName("button"))
                .below(By.xpath("//div[normalize-space()='" + productName + "']"));
        return ElementsActions.getText(driver, addToCartButtonForProduct);
    }
}
