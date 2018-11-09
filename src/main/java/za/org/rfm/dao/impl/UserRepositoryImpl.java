package za.org.rfm.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.org.rfm.dao.UserRepository;
import za.org.rfm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        String hql = "FROM User usr where  usr.username= :username";
        Query query = entityManager.createQuery(hql);
        query.setParameter("username",username.trim());
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }
}
