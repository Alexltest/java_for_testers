package ru.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.Contacts;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.contact().goTo();
        File photo = new File("src/test/resources/ava.jpg");
        System.out.println(photo.exists());
        ContactData contact = new ContactData()
                .withFirstname("Alex")
                .withLastname("L")
                .withHomePhone("4343")
                .withMobilePhone("89464")
                .withWorkPhone("445")
                .withEmail1("wv@va.ru")
                .withEmail2("wv@va.ru")
                .withEmail3("wv@va.ru")
                .withAddress("adadad")
                .withPhoto(photo)
                .withGroup("test");
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
