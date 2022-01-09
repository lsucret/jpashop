package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; // 이렇게 하면 spring boot가 annotation 보고 알아서 주입. spring boot starter data jpa가 해줌.

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // member를 반환하지 않고 id만 반환하는 이유.이 분 스타일 커맨드와 쿼리를 분리해라. 저장을 하면 가급적 사이드이펙트를 만드는 커맨드 성이기 때문에 리턴값을 안 만듬. 단, id정도는 조회.
    }
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
