package com.example.dicontainer.service;

import com.example.dicontainer.domain.Member;
import com.example.dicontainer.repository.MemberRepositoryImpl;

public class MemberService {

    private final MemberRepositoryImpl memberRepositoryImpl;

    public MemberService(MemberRepositoryImpl memberRepositoryImpl){
        this.memberRepositoryImpl = memberRepositoryImpl;
    }
    public Member join(Member member){
        memberRepositoryImpl.save(member);
        return memberRepositoryImpl.findById(member.getId());
    }
}
