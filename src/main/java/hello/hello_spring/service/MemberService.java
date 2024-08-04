package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     *
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // m이라는 이름이 이미 있다면, ifPresent = null이 아니라 값이 있다면
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        /**
         Optional<Member> result = memberRepository.findByName(member.getName());
         // m이라는 이름이 이미 있다면, ifPresent = null이 아니라 값이 있다면
         result.ifPresent(m ->{
         throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
         **/
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long MemberId){
        return memberRepository.findById(MemberId);
    }
}
