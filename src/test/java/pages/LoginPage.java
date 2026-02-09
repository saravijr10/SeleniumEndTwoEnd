package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    private By email = By.id("userEmail");
    private By password = By.id("userPassword");
    private By loginBtn = By.id("login");
    private By errorMessage = By.id("toast-container");
    private By homePageHeading = By.xpath("//h3[text()='Automation']");

    public void validLogin(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(loginBtn);
    }

    public String inValidLogin(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(loginBtn);

        //wait
        WebElement toastMessageElement = WaitUtils.waitForElementVisible(getDriver(), errorMessage);

        // Get the text from the toast message element
        String actualToastText = toastMessageElement.getText();

        // Print the toast message to the console
        System.out.println("Captured Toast Message: " + actualToastText);
        return actualToastText;
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String homePageHead(){
        WebElement headingTitle = WaitUtils.waitForElementVisible(getDriver(), homePageHeading);
        String heading = getDriver().findElement(homePageHeading).getText();

        return heading;
    }
}
