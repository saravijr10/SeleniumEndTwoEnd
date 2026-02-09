package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class BasePage {


    protected WebDriver getDriver() {

        System.out.println("Fetching driver from DriverFactory: " + DriverFactory.getDriver());
        return DriverFactory.getDriver();
    }

    protected void click(By locator) {
        WaitUtils.waitForElementClickable(getDriver(), locator).click();

    }


    protected void type(By locator, String text) {
        WaitUtils.waitForElementVisible(getDriver(), locator);
        getDriver().findElement(locator).sendKeys(text);
    }

}
