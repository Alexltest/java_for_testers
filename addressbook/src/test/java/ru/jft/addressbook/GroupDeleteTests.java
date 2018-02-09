package ru.jft.addressbook;

import org.testng.annotations.Test;
import org.openqa.selenium.*;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
