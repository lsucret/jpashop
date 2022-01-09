package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

// live template 만드는법 다시! tdd

    @Test
    @Transactional // EntityManager 안에서의 모든 데이터 변경은 트랜잭션 안에서 이뤄져야 함
    // spring framework transactional 사용을 권장. 여기서 제공하는게 많음.
    // spring framework transactional 이 test case 에 있으면 db 작업 후 롤백해버림. 그래서 h2 db가도 데이터가 없다.
    /*
    여기서 하는 일
    drop table if exists member CASCADE
    drop sequence if exists hibernate_sequence
    create table member (
       id bigint not null,
        username varchar(255),
        primary key (id)
    )
    *
    *
    * */
    public void testMember() throws Exception {

        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
}