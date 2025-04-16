package utils;

import drivers.GUIDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import static utils.TImeStampUtils.getTimeStamp;

public class ScreenshotUtils {

        public static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";
        private static final DateTimeFormatter TIMESTAMP_FORMAT =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        /**
         * Captures a screenshot using the provided driver and saves it with the test name and timestamp.
         *
         * @param testName The name of the test for naming the screenshot file.
         */
        public static void takeScreenshot(String testName) {
            String sanitizedTestName = testName.replaceAll("[^a-zA-Z0-9_-]", "_");
            String screenshotFile = String.format("%s_%s.png", sanitizedTestName,getTimeStamp());
            String filePath = SCREENSHOTS_PATH + screenshotFile;

            try {
                Files.createDirectories(Paths.get(SCREENSHOTS_PATH));
                File screenshot = ((TakesScreenshot) GUIDriver.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File(filePath));
                LogsUtils.info("Screenshot saved at: " + filePath);
                AllureUtils.attachScreenshotToAllureReport(testName, filePath);
            } catch (IOException e) {
                LogsUtils.info("Failed to save screenshot for " + testName + ": " + e.getMessage());
            }
        }
    }
