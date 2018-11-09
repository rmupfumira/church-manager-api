package za.org.rfm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.rfm.dao.UserRepository;
import za.org.rfm.entity.User;
import za.org.rfm.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User doLogin(User user) {
        User userFromDB = userRepository.getUserByUsername(user.getUsername());
        if(userFromDB != null){
            if(userFromDB.getPassword().equalsIgnoreCase(user.getPassword())){
                return userFromDB;
            }
        }
        return null;
    }
}
