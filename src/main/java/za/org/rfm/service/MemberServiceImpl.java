package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.rfm.dao.MemberRepository;
import za.org.rfm.entity.Member;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member getMemberById(Integer assemblyId) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }

    @Override
    public void addMember(Member member) {

    }

    @Override
    public void updateMembersDataBase(List<Member> members) {

        for(Member member : members){
            Member memberFromDB = memberRepository.getMemberByFullName(member.getFullName());

            if(memberFromDB == null){
                memberRepository.addMember(member);
            }
        }
    }
}
