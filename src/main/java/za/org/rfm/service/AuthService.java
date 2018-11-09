package za.org.rfm.service;

import za.org.rfm.entity.User;

public interface AuthService {

    User doLogin(User user);
}
