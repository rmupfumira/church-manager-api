package za.org.rfm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "Assembly")
public class Assembly extends DatabaseEntity{

    private String name;

    @OneToMany(mappedBy = "assembly")
    private List<Transaction> transactionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
