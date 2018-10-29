package za.org.rfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MemberLogsheet extends DatabaseEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Getter
    @Setter
    private boolean present;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "logsheetId")
    private LogSheet logSheet;

    public MemberLogsheet(Member member, LogSheet logSheet, boolean present) {
        this.member = member;
        this.present = present;
        this.logSheet = logSheet;
    }
}
