package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.rfm.dao.GuestRepository;
import za.org.rfm.entity.Guest;
import za.org.rfm.utils.SendEmail;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    GuestRepository guestRepository;

    @Override
    public void sendWebSiteEmail(Guest guest) {
        String message = guest.getName()+" has made the following enquiry from the website: \n"+guest.getMessage()+" Their email address is : "+guest.getEmail();
        guestRepository.saveGuest(guest);
        SendEmail.send(guest.getEmail(),message,"Website Enquiry");
    }
}
