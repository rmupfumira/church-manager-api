package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import za.org.rfm.dao.GuestRepository;
import za.org.rfm.entity.Guest;
import za.org.rfm.mail.Mail;
import za.org.rfm.utils.SendEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

       //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("email-template", context);
        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        emailSender.send(message);
    }

    @Override
    public void sendWebSiteEmail(Guest guest) {

        try {
            Mail mail = new Mail();
            mail.setFrom("no-reply@rfm.org.za");
            mail.setTo("russel@rfm.org.za");
            mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

            Map model = new HashMap();
            model.put("name", "Test");
            model.put("location", "Belgium");
            model.put("signature", "https://www.rfm.org.za");
            mail.setModel(model);

            sendSimpleMessage(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
