package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementsActions;

public class ConfirmationPage {
    private final WebDriver driver;

    //Locators
    private final By confirmationMessage = By.className("complete-header");

    //Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get confirmation message")
    public String getConfirmationMessage() {
        return ElementsActions.getText(driver, confirmationMessage);
    }

}
