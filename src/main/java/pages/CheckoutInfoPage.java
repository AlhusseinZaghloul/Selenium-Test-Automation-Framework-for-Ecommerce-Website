package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementsActions;

public class CheckoutInfoPage {
    private final WebDriver driver;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter First Name")
    public CheckoutInfoPage enterFirstName(String firstNameText) {
        ElementsActions.sendData(driver, firstName, firstNameText);
        return this;
    }

    @Step("Enter Last Name")
    public CheckoutInfoPage enterLastName(String lastNameText) {
        ElementsActions.sendData(driver, lastName, lastNameText);
        return this;
    }

    @Step("Enter Postal Code")
    public CheckoutInfoPage enterPostalCode(String postalCodeText) {
        ElementsActions.sendData(driver, postalCode, postalCodeText);
        return this;
    }
    @Step("Enter Shipping Data")
    public CheckoutInfoPage enterShippingData(String firstNameText, String lastNameText, String postalCodeText) {
        enterFirstName(firstNameText);
        enterLastName(lastNameText);
        enterPostalCode(postalCodeText);
        return this;
    }

    @Step("Click on Continue button")
    public CheckoutOverviewPage clickOnContinueButton() {
        ElementsActions.clicking(driver, continueButton);
        return new CheckoutOverviewPage(driver);
    }

    @Step("Click on Cancel button")
    public CheckoutInfoPage clickOnCancelButton() {
        ElementsActions.clicking(driver, cancelButton);
        return this;
    }

}
