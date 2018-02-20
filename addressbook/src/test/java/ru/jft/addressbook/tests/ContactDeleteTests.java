package ru.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete() throws InterruptedException {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().CreateContact(new ContactData("First", "Middle", "Last", "Alex", "Vgr", "Russia", "867575", "sbs@example.com", null), false);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().closeAlert();
        Thread.sleep(2000);
    }
}
