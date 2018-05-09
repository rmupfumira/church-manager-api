package za.org.rfm.service;

import za.org.rfm.entity.Guest;

public interface EmailService {

    void sendWebSiteEmail(Guest guest);
}
