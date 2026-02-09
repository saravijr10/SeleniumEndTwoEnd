package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Path screenshotsDir = Path.of(System.getProperty("user.dir"), "screenshots");
        String filename = testName + ".png";
        Path dest = screenshotsDir.resolve(filename);

        try {
            Files.createDirectories(screenshotsDir);
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saved screenshot to: " + dest.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot to " + dest + ": " + e.getMessage());
            e.printStackTrace();
        }

        return dest.toString();
    }

    public static void attachToAllure(String path) {
        try {
            Allure.addAttachment("Screenshot",
                    new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
