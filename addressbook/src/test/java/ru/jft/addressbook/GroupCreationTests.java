package ru.jft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test", "header", "footer"));
        submitGroupCreation();
        returnToGroupPage();
}

}
