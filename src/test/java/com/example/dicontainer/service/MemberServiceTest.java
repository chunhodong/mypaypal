package com.example.dicontainer.service;

import com.example.dicontainer.domain.Member;
import com.example.dicontainer.repository.MemberRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    @Test
    @DisplayName("회원을 추가하면 추가한 회원의 정보를 반환")
    void joinTest(){
        MemberRepositoryImpl memberRepositoryImpl = new MemberRepositoryImpl();
        MemberService memberService = new MemberService(memberRepositoryImpl);

        Member member = memberService.join(new Member(1l,"kim"));

        assertThat(member.getName()).isEqualTo("kim");
    }
}
