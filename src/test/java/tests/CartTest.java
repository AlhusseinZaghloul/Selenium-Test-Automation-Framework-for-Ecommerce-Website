package tests;

import drivers.GUIDriver;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CustomSoftAssertion;
import utils.JsonUtils;
import static utils.CustomSoftAssertion.softAssertion;
import static utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class CartTest {

    WebDriver driver;
    String browserName;
    JsonUtils testData;
    // product names from JSON
    String productName1, productName2, productName3, productName4;
    // expected prices from JSON
    String expectedPrice1, expectedPrice2, expectedPrice3, expectedPrice4;

    @BeforeClass
    public void init() {
        testData = new JsonUtils("testData");
        browserName = getPropertyValue("browserName");
        // Load product names from JSON
        productName1 = testData.getJsonData("valid-products.product-1.name");
        productName2 = testData.getJsonData("valid-products.product-2.name");
        productName3 = testData.getJsonData("valid-products.product-3.name");
        productName4 = testData.getJsonData("valid-products.product-4.name");
        // Load expected prices from JSON
        expectedPrice1 = testData.getJsonData("valid-products.product-1.price");
        expectedPrice2 = testData.getJsonData("valid-products.product-2.price");
        expectedPrice3 = testData.getJsonData("valid-products.product-3.price");
        expectedPrice4 = testData.getJsonData("valid-products.product-4.price");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver(browserName).get();
        new LoginPage(driver).openLoginPage(getPropertyValue("loginPageURL"))
                .performLogin(testData.getJsonData("valid-login-credentials.username"),
                        testData.getJsonData("valid-login-credentials.password"));
    }

    @Test(description = "Verify that the user can add multiple products to the cart and check their prices")
    public void addProductToCartTest() {
        new HomePage(driver)
                .addProductToCart(productName1)
                .addProductToCart(productName2)
                .addProductToCart(productName3)
                .addProductToCart(productName4)
                .clickOnCartIcon();

        CartPage cart = new CartPage(driver);

        // -- assert each price via the dynamic locator
        String actualPrice1 = cart.getProductPriceByName(productName1);
        softAssertion.assertEquals(actualPrice1, expectedPrice1,
                "Price mismatch for " + productName1);

        String actualPrice2 = cart.getProductPriceByName(productName2);
        softAssertion.assertEquals(actualPrice2, expectedPrice2,
                "Price mismatch for " + productName2);

        String actualPrice3 = cart.getProductPriceByName(productName3);
        softAssertion.assertEquals(actualPrice3, expectedPrice3,
                "Price mismatch for " + productName3);

        String actualPrice4 = cart.getProductPriceByName(productName4);
        softAssertion.assertEquals(actualPrice4, expectedPrice4,
                "Price mismatch for " + productName4);

    }

    @AfterMethod
    public void tearDown() {
        GUIDriver.quitDriver();
        CustomSoftAssertion.customAssertAll();
    }
}
