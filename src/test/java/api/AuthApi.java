package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AuthApi {

    public static Response login(String email, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("userEmail", email);
        body.put("userPassword", password);

        return ApiClient.post("/api/ecom/auth/login", body);
    }
}
