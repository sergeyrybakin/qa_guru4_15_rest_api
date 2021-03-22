package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SelenoidTests {
    //REST Assured doc:
    // github,com/rest-assured/rest-assured/wiki/usage
    // open https://selenoid.autotests.cloud/status
    // statusCode: 200
    // "errors":[]
    /*
        "browsers": {
        "android": 0,
        "chrome": 0,
        "firefox": 0,
        "opera": 0,
        "safari": 0
         },
     */

    @Test
    void successStatusTest() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200);
    }
}
