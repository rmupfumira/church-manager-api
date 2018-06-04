package za.org.rfm.dao;

import java.util.Date;
import java.util.List;

import za.org.rfm.entity.Transaction;

public interface TransactionRepository {


    public void addTransaction(Transaction transaction);

    public List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate);

    public List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate,String type);
}
