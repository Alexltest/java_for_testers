package ru.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("Alex")
                    .withLastname("L")
                    .withHomePhone("4343")
                    .withMobilePhone("89464")
                    .withWorkPhone("445")
                    .withEmail1("wv@va.ru")
                    .withEmail2("wv@va.ru")
                    .withEmail3("wv@va.ru")
                    .withAddress("adadad")
                    .withGroup("test"), true);
        }
    }

    @Test
    public void testContactModification () throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Alex").withLastname("L").withMobilePhone("89464").withEmail1("wv@va.ru").withGroup("test");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
