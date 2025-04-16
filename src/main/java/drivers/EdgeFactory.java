package drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.PropertiesUtils;

import java.util.Map;

/**
 * EdgeFactory class is responsible for creating and configuring the Edge WebDriver instance.
 * It implements the WebDriverOptionsAbstract interface to provide specific options for Edge.
 */
public class EdgeFactory extends AbstractDriver implements WebDriverOptionsAbstract <EdgeOptions> {
    @Override
    public EdgeOptions getOptions() {
        // Configure Edge-specific options for the WebDriver
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false, "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            edgeOptions.addArguments("--headless");
        }
        return edgeOptions;
    }

    @Override
    public WebDriver startDriver() {
        return new EdgeDriver(getOptions());
    }
}
