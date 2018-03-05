package ru.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().list();
        app.getContactHelper().goTo();
        ContactData contact = new ContactData("First", "Last", "867575", "sbs@example.com", "test");
        app.getContactHelper().CreateContact(contact, true);
        List<ContactData> after = app.getContactHelper().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastname);
        before.sort(comparator);
        after.sort(comparator);
        Assert.assertEquals(before, after);

    }

}
