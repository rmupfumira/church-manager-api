package za.org.rfm.client;


import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import za.org.rfm.SpringBootWebApplication;
import za.org.rfm.mail.Mail;
import za.org.rfm.service.MailService;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class MailTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    VelocityEngine velocityEngine;

    @Test
    public void sendEmail() throws Exception {
        Mail mail = new Mail();
        mail.setMailFrom("russel@rfm.org.za");
        mail.setMailTo("russel@premierlink1.co.za");
        mail.setMailSubject("Test velocity template");

        Map< String, Object > model = new HashMap<>();
        model.put("firstName", "Russ");
        model.put("lastName", "Test");
        model.put("location", "Test");
        model.put("signature", "www.rfm.com");
        mail.setModel(model);

        mailService.sendEmail(mail, getContentFromTemplate(model));
    }

    private String getContentFromTemplate(Map< String, Object > model) {

            VelocityContext context = new VelocityContext();
            context.put("firstName", model.get("firstName"));
            context.put("lastName", model.get("lastName"));
            context.put("location", model.get("location"));
            context.put("signature", model.get("signature"));
            StringWriter stringWriter = new StringWriter();
            velocityEngine.mergeTemplate("/templates/velocity/email-template.vm", "UTF-8", context, stringWriter);
            String text = stringWriter.toString();
            return text;
    }
}
