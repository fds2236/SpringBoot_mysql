package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor // 생성자의존성 주입을 한 번에 해결
public class MemberService {
    // => 이 세 줄을 @RequiredArgsConstructor + private final MemberRepository memberRepository;로 대체
    // private final MemberRepository memberRepository;
    // public MemberService(MemberRepository memberRepository) {
    // this.memberRepository = memberRepository;
    private final MemberRepository memberRepository;
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    // 중복 체크
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
