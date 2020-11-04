package users;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;
import utils.TestUtils;

import static io.restassured.RestAssured.given;


public class usersTest {

    @Step("POST user and return status code.")
    protected Response postUser(User user) {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/users";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .filter(new AllureRestAssured())
                .when()
                .post();

        return response;
    }


    @Step("Check that user contains in list of all users.")
    protected boolean usersContains(User user) {
        return TestUtils.getAllUsers().contains(user);
    }

}