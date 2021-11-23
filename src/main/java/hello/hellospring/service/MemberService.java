package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long signUp(Member member) {
        checkDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 이름 중복 확인
    private void checkDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(existMember -> {
                    throw new IllegalStateException("Member Name Already Exist!");
                });
    }

    // 전체 회원 조회
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Optional<Member> findMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
