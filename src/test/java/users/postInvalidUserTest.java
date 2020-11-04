package users;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;


public class postInvalidUserTest extends usersTest {

    @Test
    @Epic(value = "Phonebook.")
    @Feature(value = "/users")
    @Story(value = "Negative tests.")
    @Description(value = "POST request. Body contains JSON representation of invalid user.")
    void postInvalidUser() {
        User invalidUser = new User("O", "Grabar");
        Response response = postUser(invalidUser);
        Assert.assertEquals(400, response.getStatusCode(), "Invalid response status code.");
    }

}