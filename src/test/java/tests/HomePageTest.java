package tests;

import drivers.GUIDriver;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.CustomSoftAssertion;
import utils.JsonUtils;
import utils.PropertiesUtils;

import static utils.CustomSoftAssertion.softAssertion;
import static utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class HomePageTest {
    WebDriver driver;
    String browserName;
    JsonUtils testData;

    @BeforeClass
    public void init() {
        testData = new JsonUtils("testData");
        browserName = PropertiesUtils.getPropertyValue("browserName");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver(browserName).get();
        new LoginPage(driver).openLoginPage(getPropertyValue("loginPageURL"))
                .performLogin(testData.getJsonData("valid-login-credentials.username"),
                        testData.getJsonData("valid-login-credentials.password"));
    }

    @Test(description = "Verify that the user can add a product to the cart")
    public void addProductToCartTest() {
        new HomePage(driver)
                .addProductToCart(testData.getJsonData("valid-products.product-1.name"));
        softAssertion.assertTrue(new HomePage(driver)
                .getTextOfAddToCartButton(testData.getJsonData("valid-products.product-1.name"))
                .contains("Remove"));

        new HomePage(driver).addProductToCart(testData.getJsonData("valid-products.product-2.name"));
        softAssertion.assertTrue(new HomePage(driver)
                .getTextOfAddToCartButton(testData.getJsonData("valid-products.product-2.name"))
                .contains("Remove"));
    }

    @AfterMethod
    public void tearDown() {
        GUIDriver.quitDriver();
        CustomSoftAssertion.customAssertAll();
    }

}
