package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigReader;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {

        String browser = ConfigReader.get("browser");
        System.out.println("Browser from config: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver();
            driver.set(webDriver);
            System.out.println("Driver set in ThreadLocal");
        }
        System.out.println("Driver from ThreadLocal: " + driver.get());

        getDriver().manage().window().maximize();
        getDriver().get(ConfigReader.get("url"));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

