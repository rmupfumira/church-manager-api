package za.org.rfm.service;

import za.org.rfm.entity.Member;

import java.util.List;

public interface MemberService {

    public Member getMemberById(Integer assemblyId);

    public List<Member> getAllMembers();

    public void addMember(Member member);

    public void updateMembersDataBase(List<Member> members);
}
