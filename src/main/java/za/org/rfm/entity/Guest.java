package za.org.rfm.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Guest extends DatabaseEntity{
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String message;
}
