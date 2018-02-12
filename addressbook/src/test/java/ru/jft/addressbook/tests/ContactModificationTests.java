package ru.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () throws InterruptedException {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com"));
        app.getContactHelper().submitContactModification();
        Thread.sleep(5000);
    }
}
