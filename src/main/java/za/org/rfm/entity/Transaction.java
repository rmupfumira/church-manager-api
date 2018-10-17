package za.org.rfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction extends DatabaseEntity{

    @Getter
    @Setter
    private double amount = 0;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String beneficiary;

    @Getter
    @Setter
    private String authorisedBy;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "assemblyId")
    private Assembly assembly;

}
