package ru.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCompareTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().isEmpty()) {
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
                    //.withGroup("test")
                    , true);
        }
    }

    @Test
    public void testContactPhones() {
        app.contact().goToContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s -> ! s.equals("")))
                .map(ContactsCompareTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    @Test
    public void testContactAddress() {
        app.contact().goToContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo (refactorAddress(contactInfoFromEditForm)));
    }

    private String refactorAddress(ContactData contact) {
        return Stream.of(contact.getAddress())
                .map(ContactsCompareTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    @Test
    public void testContactEmails() {
        app.contact().goToContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllMails(), equalTo (mergeMails(contactInfoFromEditForm)));
    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactsCompareTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    static String cleaned(String smth) {
        return smth.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
