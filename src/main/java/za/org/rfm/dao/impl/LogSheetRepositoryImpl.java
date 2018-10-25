package za.org.rfm.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.org.rfm.dao.LogSheetRepository;
import za.org.rfm.entity.LogSheet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class LogSheetRepositoryImpl implements LogSheetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addLogSheet(LogSheet logSheet) {
        entityManager.persist(logSheet);
    }
}
