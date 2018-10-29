package za.org.rfm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.org.rfm.dao.LogSheetRepository;
import za.org.rfm.dao.MemberRepository;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;
import za.org.rfm.entity.MemberLogsheet;
import za.org.rfm.utils.GeneralUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Transactional
@Repository
public class LogSheetRepositoryImpl implements LogSheetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void addLogSheet(LogSheet logSheet) {
        entityManager.persist(logSheet);
        for (Map.Entry<String, String> entry : logSheet.getAttendance().entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            Member member = memberRepository.getMemberByFullName(entry.getKey());
            if(member != null){
                MemberLogsheet memberLogsheet = new MemberLogsheet(member,logSheet, GeneralUtils.isPresent(entry.getValue()));
                entityManager.persist(memberLogsheet);
            }
        }
    }
}
