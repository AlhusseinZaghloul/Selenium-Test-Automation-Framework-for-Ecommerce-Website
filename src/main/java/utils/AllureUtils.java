package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    private AllureUtils(){}

    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    /**
     * Attaches the latest log file to the Allure report.
     * The log file is retrieved from the specified logs directory.
     * If the log file does not exist or cannot be read, an error message is logged.
     */
    public static void attachLogsToAllureReport(){
        // Get the latest log file from the logs directory
        File logFile = FilesUtils.getLatestFile(LogsUtils.LOGS_PATH);
        // Check if the log file exists
        if (logFile != null && logFile.exists()){
            try (InputStream is = new FileInputStream(logFile)) {
                // Attach log file content as text
                Allure.addAttachment("Execution Logs", Files.readString(logFile.toPath()));
            } catch (FileNotFoundException e) {
                LogsUtils.error("Log file not found at: ", logFile.getAbsolutePath());
            } catch (Exception e) {
                LogsUtils.error("Failed to attach logs to Allure report: ", e.getMessage());
            }
        } else {
            LogsUtils.warn("No log file found in directory: ", LogsUtils.LOGS_PATH);
        }
    }
    /**
     * Attaches a screenshot to the Allure report using the specified file path.
     *
     * @param attachmentName The name to display in the Allure report (includes test status).
     * @param screenshotPath The file path of the screenshot to attach.
     */
    public static void attachScreenshotToAllureReport(String attachmentName, String screenshotPath) {
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.exists()) {
            try (InputStream is = new FileInputStream(screenshotFile)) {
                // Attach screenshot as PNG image
                Allure.addAttachment(attachmentName, "image/png", is, "png");
            } catch (Exception e) {
                LogsUtils.error("Failed to attach screenshot to Allure report: " + e.getMessage());
            }
        } else {
            LogsUtils.warn("Screenshot file not found: " + screenshotPath);
        }
    }
}
