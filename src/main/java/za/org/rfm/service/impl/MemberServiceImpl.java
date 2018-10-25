package za.org.rfm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.rfm.dao.AssemblyRepository;
import za.org.rfm.dao.MemberRepository;
import za.org.rfm.entity.Assembly;
import za.org.rfm.entity.Member;
import za.org.rfm.service.MemberService;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AssemblyRepository assemblyRepository;


    @Override
    public Member getMemberById(Integer assemblyId) {
        return null;
    }

    @Override
    public List<Member> getAllMembers(Integer assemblyId) {
        return memberRepository.getAllMembers(assemblyId);
    }

    @Override
    public void addMember(Member member, Integer id) {
        Assembly assembly = assemblyRepository.getAssemblyById(id);
        if(assembly != null){
            member.setAssembly(assembly);
            memberRepository.addMember(member);
            logger.info("Member "+member.getFullName()+" has been added to the database under "+assembly.getName()+" assembly");
        }else{
            logger.warn("Assembly with id:  "+id+" could not be found! Member has NOT been added");
        }
    }

    @Override
    public void updateMembersDataBase(List<Member> members, Integer id) {
        int count = 0;
        for(Member member : members){
            Member memberFromDB = memberRepository.getMemberByFullName(member.getFullName());

            if(memberFromDB == null){
                count++;
                addMember(member,id);
            }
        }

        logger.info(count+" members have been added to the database ");

    }
}
