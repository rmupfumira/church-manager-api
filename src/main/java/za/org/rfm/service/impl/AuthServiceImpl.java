package za.org.rfm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.rfm.dao.AssemblyRepository;
import za.org.rfm.dao.UserRepository;
import za.org.rfm.entity.Assembly;
import za.org.rfm.entity.User;
import za.org.rfm.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AssemblyRepository assemblyRepository;

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

    @Override
    public void addUser(User user) {

        Integer assemblyId =  user.getAssemblyId();
        Assembly assembly = assemblyRepository.getAssemblyById(assemblyId);
        if(assembly != null){
            user.setAssembly(assembly);
            userRepository.addUser(user);
        }else{
            logger.error("Could not create user because assembly is null");
        }
    }
}
