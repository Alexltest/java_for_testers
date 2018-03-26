package ru.jft.mantis.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        app.getDriver();
        wd.findElement(id("username")).sendKeys("administrator");
        click(xpath("//form[@id='login-form']/fieldset/input[2]"));
        wd.findElement(id("password")).sendKeys("root");
        click(xpath("//form[@id='login-form']/fieldset/input[3]"));
    }

    public void userPassChange() {
        click(linkText("управление"));
        click(linkText("Управление пользователями"));
        click(linkText("user1"));
        click(xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }

    public void finish(String confirmationLink) {
        wd.get(confirmationLink);
        wd.findElement(name("password")).sendKeys("password1");
        wd.findElement(name("password_confirm")).sendKeys("password1");
        click(By.xpath("//span[@class='submit-button']/button"));
    }
}
