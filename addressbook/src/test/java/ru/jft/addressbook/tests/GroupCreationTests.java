package ru.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.jft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test");
        app.group().create(group);
        Set<GroupData> after = app.group().all();

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
