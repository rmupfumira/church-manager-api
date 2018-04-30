package za.org.rfm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TransactionResponseObj implements Serializable {

    @Getter
    private List<Transaction> transactionList = new ArrayList<>();

    @Getter
    @Setter
    private double totalIncome;

    @Getter
    @Setter
    private double totalExpenses;

    @Getter
    @Setter
    private double netIncome;


    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;

        for(Transaction transaction : transactionList){
            if(transaction.getType().equalsIgnoreCase("Income")){
                this.totalIncome += transaction.getAmount();
            }
            else {
                this.totalExpenses += transaction.getAmount();
            }
        }
        this.netIncome = this.totalIncome - this.totalExpenses;
    }
}
