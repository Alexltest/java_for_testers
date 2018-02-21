package ru.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () throws InterruptedException {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().CreateContact(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com", null), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com", null), false);
        app.getContactHelper().submitContactModification();
        Thread.sleep(2000);
    }
}
