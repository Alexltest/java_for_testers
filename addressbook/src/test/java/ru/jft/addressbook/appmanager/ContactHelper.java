package ru.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.Contacts;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(
                    By.name("new_group")
            )).selectByVisibleText(
                    contactData.getGroup()
            );
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void goTo() {
        click(By.linkText("add new"));
    }

    public void goToContactPage() {
        click(By.linkText("home"));
    }

    public void select(int id) {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void delete(ContactData contact) {
        select(contact.getId());
        deleteContact();
        contactCache = null;
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(ContactData contact, boolean b) {
        goTo();
        fillContactForm(contact, b);
        submitContactCreation();
        contactCache = null;
        goToContactPage();
    }

    public void modify(ContactData contact) throws InterruptedException {
        select(contact.getId());
        initContactModification();
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        Thread.sleep(5000);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastname));
        }
        return new Contacts(contactCache);
    }
}
