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

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void select() {
        click(By.name("selected[]"));
    }

    public void delete() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
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
        returnToContactPage();
    }

    public void modify(ContactData contact) throws InterruptedException {
        select();
        initContactModification();
        fillContactForm(contact, false);
        submitContactModification();
        Thread.sleep(5000);
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(name).withLastname(lastname));
        }
        return contacts;
    }
}
