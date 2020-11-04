package users;

import io.qameta.allure.*;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class postValidUserTest extends usersTest {

    @Test
    @Epic(value = "Phonebook.")
    @Feature(value = "/users")
    @Story(value = "Positive tests.")
    @Description(value = "POST request. Body contains JSON representation of valid user.")
    void postValidUser() {
        User validUser = new User("Oleg", "Grabar");
        Response response = postUser(validUser);
        User addedUser = response
                .then()
                .extract()
                .body()
                .jsonPath()
                .getObject("", User.class);
        Assert.assertTrue(List.of(200, 201).contains(response.getStatusCode()), "Invalid response status code.");
        Assert.assertTrue(usersContains(addedUser), "User isn't contained in list of all users.");
    }

}