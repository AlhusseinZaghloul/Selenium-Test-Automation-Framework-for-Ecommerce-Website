package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private static final String PROPERTIES_PATH = "src/main/resources/";

    public static Properties loadProperties() {

        try {
            Properties properties = new Properties();
            Collection<File> propertiesFilesList;
            propertiesFilesList = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true);
            propertiesFilesList.forEach(propertyFile -> {
                try (FileInputStream fileInputStream = new FileInputStream(propertyFile)) {
                    properties.load(fileInputStream);
                } catch (IOException ioe) {
                    LogsUtils.error(ioe.getMessage());
                }
            });
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);
            LogsUtils.info("Loading Properties File Data");
            return properties;
        } catch (Exception e) {
            LogsUtils.error("Failed to Load Properties File Data because: " + e.getMessage());
            return null;
        }
    }

    // Get the value of the property
    public static String getPropertyValue(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "";
        }
    }
}
