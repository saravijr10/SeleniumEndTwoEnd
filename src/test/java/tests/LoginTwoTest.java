package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTwoTest extends BaseTest {

    @Test(dataProvider = "validLoginData", dataProviderClass = utils.DataProviders.class,groups = "regression", retryAnalyzer = utils.RetryAnalyzer.class)
    @Description("Verify user can login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyLoginPageTitle(String email, String password) {

        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(email, password);
        String title = loginPage.getTitle();
        Assert.assertTrue(title.contains("Let's Shop"));

    }

    @Test(dataProvider = "validLoginData", dataProviderClass = utils.DataProviders.class,groups = "regression", retryAnalyzer = utils.RetryAnalyzer.class)
    @Description("Verify user can login with valid credentials and check home page displayed as expected")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyValidLogin(String email, String password) {

        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(email, password);
        String heading = loginPage.homePageHead();
        System.out.println(heading);
        Assert.assertTrue(heading.contentEquals("AUTOMATION"));

    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = utils.DataProviders.class,groups = "regression", retryAnalyzer = utils.RetryAnalyzer.class)
    @Description("Verify user can login with invalid credentials and getting error message as expected")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyInValidLogin(String email, String password) {

        LoginPage loginPage = new LoginPage();
        String errorMessage = loginPage.inValidLogin(email, password);
        Assert.assertTrue(errorMessage.contentEquals("Incorrect email or password."));

    }


    @Test(dataProvider = "validLoginData", dataProviderClass = utils.DataProviders.class,groups = {"smoke", "regression"}, retryAnalyzer = utils.RetryAnalyzer.class)
    @Description("Verify user can login with valid credentials but home page title assertion fail on testcases")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyValidLoginFail(String email, String password) {

        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(email, password);
        String heading = loginPage.homePageHead();
        Assert.assertTrue(heading.contentEquals("Automation"));

    }

}
