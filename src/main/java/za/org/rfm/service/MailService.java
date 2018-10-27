package za.org.rfm.service;

import za.org.rfm.mail.Mail;

public interface MailService {

    public void sendEmail(Mail mail, String contentAsString);
}
