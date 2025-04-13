package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ElementsActions;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
     //Locators
    private final By username= By.id("user-name");
    private final By password= By.id("password");
    private final By loginButton= By.id("login-button");
    private final By errorMessage= By.xpath("//h3[@data-test='error']");

    public LoginPage openLoginPage() {
        BrowserActions.navigateToUrl(driver, "https://www.saucedemo.com/");
        return this;
    }
    public LoginPage enterUsername(String userName) {
        ElementsActions.sendData(driver, username, userName);
        return this;
    }
    public LoginPage enterPassword(String passWord) {
        ElementsActions.sendData(driver, password, passWord);
        return this;
    }
    public LoginPage clickLoginButton() {
        ElementsActions.clicking(driver, loginButton);
        return this;
    }
    public String getErrorMessage() {
        return ElementsActions.getText(driver, errorMessage);
    }



}
