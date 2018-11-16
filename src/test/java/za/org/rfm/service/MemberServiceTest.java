package za.org.rfm.service;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import za.org.rfm.dao.MemberRepository;
import za.org.rfm.entity.Gender;
import za.org.rfm.entity.Member;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testCreateMember(){
        // given
        Member testmember = createMember();
        entityManager.persist(testmember);
        entityManager.flush();

        // when

        Member found = memberRepository.getMemberById(1);

        // then

        assertThat(found.getFullName())
                .isEqualTo(testmember.getFullName());

    }

    private Member createMember() {

        String firstname = "Test-"+Math.random();
        String lastname = "Testing-"+Math.random();
        return new Member(firstname,lastname,Gender.Female,"07635353464","South African","Ushering","Zone 1");
    }

}
