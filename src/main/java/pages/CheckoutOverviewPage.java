package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementsActions;

public class CheckoutOverviewPage {
    private final WebDriver driver;

    //Locators
    private final By finishButton = By.id("finish");

    //Constructor
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Finish button")
    public CheckoutComplete clickOnFinishButton() {
        ElementsActions.clicking(driver, finishButton);
        return new CheckoutComplete(driver);
    }
}
