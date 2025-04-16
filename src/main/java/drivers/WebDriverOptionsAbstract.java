package drivers;

/**
 * This interface defines a contract for classes that provide WebDriver options.
 * It is generic and can be implemented for different WebDriver types.
 *
 * @param <T> The type of WebDriver options (e.g., ChromeOptions, FirefoxOptions)
 */
public interface WebDriverOptionsAbstract<T> {
    T getOptions();
}
