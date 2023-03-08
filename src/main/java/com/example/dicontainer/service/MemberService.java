package com.example.dicontainer.service;

import com.example.dicontainer.domain.Member;
import com.example.dicontainer.repository.MemberRepository;

public class MemberService {

    public MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public Member join(Member member){
        memberRepository.save(member);
        return memberRepository.findById(member.getId());
    }
}
