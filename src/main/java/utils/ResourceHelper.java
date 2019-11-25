package utils;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;


public class ResourceHelper {

    public static Response get(String token, String endPoint) {
        return given()
                .header("Authorization", "Bearer " + Properties.oathToken)
                .header("Content-Type", "application/json")
                .when()
                .get(endPoint);
    }

    public static ResponseBody post(String token, String endPoint, String payload) {
        ResponseBody response = given().header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .body(payload)
                .post(endPoint).andReturn().then().statusCode(200).extract().response().getBody();

        return response;

    }


}
