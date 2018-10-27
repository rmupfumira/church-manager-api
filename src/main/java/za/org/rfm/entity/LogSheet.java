package za.org.rfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Map;

@Entity(name = "LogSheet")
public class LogSheet extends DatabaseEntity {


    @Getter
    @Setter
    @Transient
    String assemblyId;

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
}
