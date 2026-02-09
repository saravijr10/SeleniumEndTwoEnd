package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    static {
        RestAssured.baseURI = "https://rahulshettyacademy.com/client";
    }

    public static Response post(String endpoint, Object body) {

        return given()
                .relaxedHTTPSValidation()
                .redirects().follow(true)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }


}
