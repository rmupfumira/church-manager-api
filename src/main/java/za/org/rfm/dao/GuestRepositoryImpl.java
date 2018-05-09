package za.org.rfm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import za.org.rfm.entity.Guest;

@Transactional
@Repository
public class GuestRepositoryImpl implements GuestRepository{


    @PersistenceContext
    private EntityManager entityManager;

    public void saveGuest(Guest guest){
        entityManager.persist(guest);
    }
}
