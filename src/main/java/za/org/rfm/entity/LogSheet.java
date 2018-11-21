package za.org.rfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity(name = "LogSheet")
public class LogSheet extends DatabaseEntity {


    @Getter
    @Setter
    @Transient
    Integer assemblyId;

    @Getter
    @Setter
    String eventDate;

    @Transient
    @Getter
    @Setter
    Map<String,String> attendance;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "assemblyId")
    private Assembly assembly;

    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "logSheet")
    private List<MemberLogsheet> memberLogsheets;

    public LogSheet() {
    }

    public int getTotalAttendance() {

        int count = 0;

        for (MemberLogsheet memberLogSheetEntry :
                memberLogsheets) {
            if(memberLogSheetEntry.isPresent()){
                count++;
            }
        }
        return count;
    }
}
