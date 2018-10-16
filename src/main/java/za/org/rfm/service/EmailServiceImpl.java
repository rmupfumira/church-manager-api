package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import za.org.rfm.dao.GuestRepository;
import za.org.rfm.entity.Guest;
import za.org.rfm.mail.Mail;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    public JavaMailSender emailSender;


    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public void sendWebSiteEmail(Guest guest) {


    }
}
