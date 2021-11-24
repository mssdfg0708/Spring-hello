package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceIntegrationTest(MemberService memberService,
                                        MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Test
    void signUp() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long savedId = memberService.signUp(member);

        //then
        Member findMember = memberService.findMemberId(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void DuplicatedMemberException() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.signUp(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.signUp(member2));
        assertThat(e.getMessage()).isEqualTo("Member Name Already Exist!");
    }
}