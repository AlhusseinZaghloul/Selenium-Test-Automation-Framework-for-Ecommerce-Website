package drivers;

import org.openqa.selenium.WebDriver;

/**
 * Abstract class to define the structure for different WebDriver implementations.
 * Each specific WebDriver implementation (like Chrome, Firefox, etc.) should extend this class
 * and implement the startDriver method to return an instance of the respective WebDriver.
 */
public abstract class AbstractDriver {
    public abstract WebDriver startDriver();
}


