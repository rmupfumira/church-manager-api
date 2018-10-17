package za.org.rfm.service;

import za.org.rfm.entity.Guest;

public interface EmailService {

    public void prepareAndSend(String recipient, String message);
}
