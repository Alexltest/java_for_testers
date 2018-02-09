package ru.jft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete() throws InterruptedException {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().closeAlert();
        Thread.sleep(5000);
    }
}
