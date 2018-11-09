package za.org.rfm.dao;

import za.org.rfm.entity.User;

public interface UserRepository {

    User getUserByUsername(String username);
}
