package za.org.rfm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.org.rfm.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member getMemberById(Integer memberId) {
        return entityManager.find(Member.class, memberId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Member> getAllMembers(Integer assemblyId) {
        String hql = "FROM Member m where m.assembly.id= :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id",assemblyId);
        return (List<Member>)query.getResultList();
    }

    @Override
    public void addMember(Member member) {
        entityManager.persist(member);
    }

    @Override
    public Member getMemberByFullName(String fullName) {
        Member foundMembers = null;
        String hql = "FROM Member m where  m.fullName= :fullName";
        Query query = entityManager.createQuery(hql);
        query.setParameter("fullName",fullName);
        List<Member> members = (List<Member>) query.getResultList();
        if (members != null && !members.isEmpty()) {
            foundMembers = members.get(0);
        }

        return foundMembers;
    }

}
