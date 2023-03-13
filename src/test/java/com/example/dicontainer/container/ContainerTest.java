package com.example.dicontainer.container;

import com.example.dicontainer.repository.MemberRepository;
import com.example.dicontainer.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContainerTest {

    @Test
    @DisplayName("컨테이너에 회원레파지토리 클래스타입을 생성자인자로 입력하면 빈객체 생성")
    void createContainerWithMemberRep(){
        var classes = new HashSet<Class<?>>();
        classes.add(MemberRepository.class);

        DiContainer container = new DiContainer(classes);
        MemberRepository memberRepository = container.getBean(MemberRepository.class);

        assertThat(memberRepository).isNotNull();
    }

    @Test
    @DisplayName("컨테이너에 클래스타입을 생성자인자로 입력하면 빈객체 생성")
    void createContainer(){
        var classes = new HashSet<Class<?>>();
        classes.add(MemberService.class);
        classes.add(MemberRepository.class);

        DiContainer container = new DiContainer(classes);
        MemberRepository memberRepository = container.getBean(MemberRepository.class);
        MemberService MemberService = container.getBean(MemberService.class);

        assertThat(memberRepository).isNotNull();
        assertAll(
                () -> assertThat(memberRepository).isNotNull(),
                () -> assertThat(MemberService).isNotNull());
    }
}
