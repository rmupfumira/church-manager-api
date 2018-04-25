package za.org.rfm.service;

import java.util.Date;
import java.util.List;

import za.org.rfm.entity.Transaction;

public interface TransactionService {

    public void addTransaction(Transaction transaction,Integer assemblyId);

    public List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate);
}
