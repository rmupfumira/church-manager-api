package za.org.rfm.dao;

import java.util.Date;
import java.util.List;

import za.org.rfm.entity.Transaction;

public interface TransactionRepository {

    void addTransaction(Transaction transaction);

    List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate);

    List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate,String type);
}
