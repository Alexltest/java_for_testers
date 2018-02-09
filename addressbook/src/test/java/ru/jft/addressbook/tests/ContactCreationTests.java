package ru.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.gotoContactPage();
        app.fillContactForm(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com", "1990"));
        app.submitContactCreation();
        app.gotoHomePage();
    }

}
