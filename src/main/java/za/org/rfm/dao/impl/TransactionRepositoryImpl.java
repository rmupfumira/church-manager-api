package za.org.rfm.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import za.org.rfm.dao.TransactionRepository;
import za.org.rfm.entity.Transaction;

@Transactional
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAssembly(Integer assemblyId,Date startDate, Date endDate,String type) {
            String hql = "FROM Transaction t where t.assembly.id = :assemblyId AND created BETWEEN :startDate AND :endDate AND type = :type";
            return (List<Transaction>)entityManager.createQuery(hql)
                    .setParameter("assemblyId",assemblyId)
                    .setParameter("startDate",startDate)
                    .setParameter("endDate",endDate)
                    .setParameter("type",type)
                    .getResultList();
    }

    @Override
    public List<Transaction> getTransactionsByAssembly(Integer assemblyId,Date startDate, Date endDate) {

            String hql = "FROM Transaction t where t.assembly.id = :assemblyId AND created BETWEEN :startDate AND :endDate ";
            return (List<Transaction>)entityManager.createQuery(hql)
                    .setParameter("assemblyId",assemblyId)
                    .setParameter("startDate",startDate)
                    .setParameter("endDate",endDate)
                    .getResultList();
        }

}
