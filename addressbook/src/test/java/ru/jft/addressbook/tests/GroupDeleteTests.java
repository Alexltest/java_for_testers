package ru.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testGroupDelete() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);

    }
}
