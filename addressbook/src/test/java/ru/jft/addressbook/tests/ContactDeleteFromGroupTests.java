package ru.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.Contacts;
import ru.jft.addressbook.model.GroupData;
import ru.jft.addressbook.model.Groups;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().isEmpty()) {
            Groups groups = app.db().groups();
            GroupData groupData;

            if (groups.isEmpty()) {
                app.goTo().GroupPage();
                groupData = new GroupData().withName("test").withHeader("header").withFooter("footer");
                app.group().create(groupData);
            }
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
                            , true);
        }
    }

    @Test
    public void testContactDeleteFromGroup() {
        Contacts before = app.db().contacts();
        ContactData removedContact = before.iterator().next();
        Groups list = app.db().groups();
        GroupData firstgroup = list.iterator().next();
        app.contact().remove(removedContact, firstgroup);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}
