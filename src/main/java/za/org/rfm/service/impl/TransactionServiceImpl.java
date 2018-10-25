package za.org.rfm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import za.org.rfm.dao.AssemblyRepository;
import za.org.rfm.dao.TransactionRepository;
import za.org.rfm.entity.Assembly;
import za.org.rfm.entity.Transaction;
import za.org.rfm.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Override
    public void addTransaction(Transaction transaction,Integer assemblyId) {
        Assembly assembly = assemblyRepository.getAssemblyById(assemblyId);
        if(assembly != null){
            transaction.setAssembly(assembly);
            transactionRepository.addTransaction(transaction);
        }
    }

    @Override
    public List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate) {
        return transactionRepository.getTransactionsByAssembly(assemblyId,startDate,endDate);
    }

    @Override
    public List<Transaction> getTransactionsByAssembly(Integer assemblyId, Date startDate, Date endDate,String type) {
        return transactionRepository.getTransactionsByAssembly(assemblyId,startDate,endDate,type);
    }
}
