package za.org.rfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity(name = "User")
public class User extends DatabaseEntity{

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "assemblyId")
    private Assembly assembly;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getEmailAddress(), user.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFullName(), getEmailAddress());
    }
}
