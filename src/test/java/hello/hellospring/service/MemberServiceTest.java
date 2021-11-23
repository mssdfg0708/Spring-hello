package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
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

    @Test
    void findAllMembers() {
    }

    @Test
    void findMemberId() {
    }
}