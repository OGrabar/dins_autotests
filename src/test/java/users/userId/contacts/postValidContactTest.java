package users.userId.contacts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

public class postValidContactTest extends contactsTest {

    @Test
    @Epic(value = "Phonebook.")
    @Feature(value = "/users/{userId}/contacts")
    @Story(value = "Positive tests.")
    @Description(value = "POST request. User exists. Body contains JSON representation of valid contact.")
    void putValidContact() {
        User user = TestUtils.getFirstUserInList();
        Contact validContact = new Contact(1, "John", "Brown", "1112223334", "a@asd.ru");
        Response response = postContact(user.getId(), validContact);
        Contact addedContact = response
                .then()
                .extract()
                .body()
                .jsonPath()
                .getObject("", Contact.class);
        Assert.assertTrue(List.of(200, 201).contains(response.getStatusCode()), "Invalid response status code.");
        Assert.assertTrue(contactsContains(user.getId(), addedContact), "Added contact isn't contained in list of all users.");
    }
}
