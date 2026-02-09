package utils;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());

        String testName = result.getName();
        String filename = testName + "_" + System.currentTimeMillis();

        WebDriver driver = DriverFactory.getDriver();

        // Fallback: try to get driver from the test instance via reflection (field named 'driver')
        if (driver == null) {
            try {
                Object testInstance = result.getInstance();
                Field f = testInstance.getClass().getDeclaredField("driver");
                f.setAccessible(true);
                driver = (WebDriver) f.get(testInstance);
            } catch (Exception e) {
                System.err.println("Could not obtain WebDriver from test instance: " + e.getMessage());
            }
        }

        if (driver != null) {
            try {
                // Save a PNG file locally and attach to Allure
                String path = utils.ScreenshotUtils.capture(driver, filename);
                utils.ScreenshotUtils.attachToAllure(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("WebDriver is null; cannot capture screenshot for: " + testName);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }
}
