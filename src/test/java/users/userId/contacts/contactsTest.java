package users.userId.contacts;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Contact;;
import utils.TestUtils;

import static io.restassured.RestAssured.given;


public abstract class contactsTest {

    @Step("POST contact. userId equals {userId}.")
    protected Response postContact(int userId, Contact contact) {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/users/" + userId + "/contacts";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(contact)
                .filter(new AllureRestAssured())
                .when()
                .post();

        return response;
    }


    @Step("Check that contact contains in list of all user's contacts.")
    protected boolean contactsContains(int userId, Contact contact) {
        return TestUtils.getAllContactsOfUser(userId).contains(contact);
    }


}