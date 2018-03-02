package ru.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete() throws InterruptedException {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().CreateContact(new ContactData("First", "Last", "867575", "sbs@example.com", "test"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().closeAlert();
        Thread.sleep(5000);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastname);
        before.sort(comparator);
        after.sort(comparator);
        Assert.assertEquals(before, after);
    }
}
