package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.rfm.dao.GuestRepository;
import za.org.rfm.entity.Guest;
import za.org.rfm.mail.Mail;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    GuestRepository guestRepository;


    public void sendSimpleMessage(Mail mail) {

    }

    @Override
    public void sendWebSiteEmail(Guest guest) {


    }
}
