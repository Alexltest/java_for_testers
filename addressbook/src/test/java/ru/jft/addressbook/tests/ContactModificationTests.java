package ru.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getContactHelper().list().size() == 0) {
            app.getContactHelper().CreateContact(new ContactData("First", "Last", "867575", "sbs@example.com", "test"), true);
        }
    }

    @Test
    public void testContactModification () throws InterruptedException {
        List<ContactData> before = app.getContactHelper().list();
        ContactData contact = new ContactData("First", "Last", "867575", "sbs@example.com", "test");
        app.getContactHelper().modify(contact);
        List<ContactData> after = app.getContactHelper().list();

        Assert.assertEquals(after.size(), before.size());

        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastname);
        before.sort(comparator);
        after.sort(comparator);
        Assert.assertEquals(before, after);
    }

}
