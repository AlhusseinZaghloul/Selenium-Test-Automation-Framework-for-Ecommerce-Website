package listeners;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    @Override
    public void onExecutionStart() {
        LogsUtils.info("==================== Test Execution Started ====================");
        // Load properties file
        PropertiesUtils.loadProperties();
        FilesUtils.deleteFiles(new File(AllureUtils.ALLURE_RESULTS_PATH));
        // Clean directories for logs folder
        FilesUtils.cleanDirectory(new File(LogsUtils.LOGS_PATH));
        // Clean directories for screenshots folder
        FilesUtils.cleanDirectory(new File(ScreenshotUtils.SCREENSHOTS_PATH));
        LogsUtils.info("Cleaned output directories (Allure Results, Logs, Screenshots).");
    }

    @Override
    public void onExecutionFinish() {
        LogsUtils.info("==================== Test Execution Finished ====================");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotUtils.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotUtils.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotUtils.takeScreenshot("skipped-" + testResult.getName());
            }
        }
        AllureUtils.attachLogsToAllureReport();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test case" , result.getName() , "passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test case " , result.getName() , " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test case" , result.getName() , "skipped");
    }


}
