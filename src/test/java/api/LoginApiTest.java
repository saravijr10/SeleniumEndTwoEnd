package tests;

import api.AuthApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginApiTest {

    @Test
    public void loginAPITest() {

        Response response = AuthApi.login(
                "saravanaplaywright.com",
                "QATesting1!"
        );

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.asString().contains("token"));
    }
}
