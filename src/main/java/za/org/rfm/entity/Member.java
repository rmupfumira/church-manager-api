package za.org.rfm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "Member")
public class Member extends DatabaseEntity{

    @Getter
    @Setter
    public String fullName;


    @Getter
    public Gender gender;

    @Getter
    @Setter
    public String phone;

    @Getter
    @Setter
    public String nationality;

    @Getter
    @Setter
    public String department;

    @Getter
    @Setter
    public String homeChurch;

    @Getter
    @Setter
    public String maritalStatus;

    public Member() {
    }

    public Member(String firstName, String lastName, Gender gender, String phone, String nationality, String department, String homeChurch) {
        this.fullName = firstName;
        this.gender = gender;
        this.phone = phone;
        this.nationality = nationality;
        this.department = department;
        this.homeChurch = homeChurch;
    }

    public void setGender(String gender) {
        if("M".equalsIgnoreCase(gender)){
            this.gender = Gender.Male;
        }
        else {
            this.gender = Gender.Female;
        }
    }
}
