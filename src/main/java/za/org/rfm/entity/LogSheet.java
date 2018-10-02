package za.org.rfm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Map;

public class LogSheet extends DatabaseEntity {

    @Getter
    @Setter
    String eventDate;

    @Getter
    @Setter
    Map<String,String> attendance;
}
