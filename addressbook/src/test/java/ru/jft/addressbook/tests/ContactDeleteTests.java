package ru.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {

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
    public void testContactDelete() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Thread.sleep(5000);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
