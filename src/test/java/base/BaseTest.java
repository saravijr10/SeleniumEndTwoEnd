package base;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;

import java.io.ByteArrayInputStream;

@Listeners(TestListener.class)
public class BaseTest {

    protected Logger log = LogManager.getLogger(this.getClass());

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        System.out.println("SETUP RUNNING");
        log.info("Initializing driver");

        //for parallel execution
        DriverFactory.initializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }

        DriverFactory.quitDriver();
    }

    public void captureScreenshot(String testName) {

        byte[] screenshot =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Failed Screenshot - " + testName,
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }

}
