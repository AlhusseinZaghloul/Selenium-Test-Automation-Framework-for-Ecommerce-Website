package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementsActions;

public class CheckoutComplete {
    private final WebDriver driver;

    //Locators
    private final By checkoutComplete = By.className("complete-header");

    //Constructor
    public CheckoutComplete(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get Checkout Complete text")
    public String getCheckoutCompleteText() {
        return ElementsActions.getText(driver, checkoutComplete);
    }

}
