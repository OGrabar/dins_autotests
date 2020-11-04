package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import model.Contact;
import model.User;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public final class TestUtils {

    private TestUtils() {

    }

    public static List<User> getAllUsers() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/users";

        JsonPath jsonPath = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .when()
                .get()
                .then()
                .extract()
                .body()
                .jsonPath();

        return jsonPath.getList("", User.class);
    }

    public static List<Contact> getAllContactsOfUser(int userId) {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/users/" + userId + "/contacts";

        JsonPath jsonPath = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .when()
                .get()
                .then()
                .extract()
                .body()
                .jsonPath();

        return jsonPath.getList("", Contact.class);
    }

    public static User getFirstUserInList() {
        List<User> users = getAllUsers();
        Assert.assertTrue(users.size() > 0, "No users exist.");
        return users.get(0);
    }


}
