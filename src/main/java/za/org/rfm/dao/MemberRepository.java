package za.org.rfm.dao;

import za.org.rfm.entity.Member;

import java.util.List;

public interface MemberRepository {

    public Member getMemberById(Integer memberId);

    public List<Member> getAllMembers(Integer assemblyId);

    public void addMember(Member member);

    public Member getMemberByFullName(String fullName);

}
