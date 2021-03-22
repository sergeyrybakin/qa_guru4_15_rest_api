package tests;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Test
    void successWithoutGivenWhenStatusTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusWithBasicAuthTest() {
        given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusResponceWithLogTest() {
        given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    void successStatusReadyTest() {
        given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    void successStatusReadyWithAssertThatTest() {
        Boolean result = given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .path("value.ready");

        assertThat(result, is(true));
    }

    @Test
    void successStatusReadyWithAssertThat1Test() {
        ExtractableResponse<Response> result = given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .extract();

        assertThat(result.path("value.ready"), is(true));
    }
}
