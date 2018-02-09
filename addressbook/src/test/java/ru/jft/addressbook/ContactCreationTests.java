package ru.jft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoContactPage();
        fillContactForm(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com", "1990"));
        submitContactCreation();
        gotoHomePage();
    }

}
