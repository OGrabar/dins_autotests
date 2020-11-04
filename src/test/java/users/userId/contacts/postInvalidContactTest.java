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



public class postInvalidContactTest extends contactsTest {

    @Test
    @Epic(value = "Phonebook.")
    @Feature(value = "/users/{userId}/contacts")
    @Story(value = "Negative tests.")
    @Description(value = "POST request. User exists. Body contains JSON representation of invalid contact (phone " +
            "contains only 9 digits).")
    void postValidContact() {
        User user = TestUtils.getFirstUserInList();
        Contact invalidContact = new Contact("John", "Brown", "112223334", "@asd");
        Response response = postContact(user.getId(), invalidContact);
        Assert.assertEquals(400, response.getStatusCode(), "Invalid response status code.");
      }
}
