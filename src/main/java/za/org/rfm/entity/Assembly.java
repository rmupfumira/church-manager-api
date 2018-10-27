package za.org.rfm.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity(name = "Assembly")
public class Assembly extends DatabaseEntity{

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "assembly")
    private List<Transaction> transactionList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "assembly")
    private List<Member> members;

    @Getter
    @Setter
    @OneToMany(mappedBy = "assembly",fetch= FetchType.EAGER)
    private List<User> users;


}
