package ru.jft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ChangeUserPassByAdminTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangeUserPassByAdmin() throws IOException {
       String email = "user1@localhost.localdomain";
       app.admin().login();
       app.admin().userPassChange();
       List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
       String confirmationLink = findChangePasswordLink(mailMessages, email);
       app.admin().finish(confirmationLink);
       assertTrue(app.newSession().login("user1", "password1"));
    }

    private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
