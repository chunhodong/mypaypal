package com.example.dicontainer.container;

import com.example.dicontainer.repository.MemberRepositoryImpl;
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
        classes.add(MemberRepositoryImpl.class);

        DiContainer container = new DiContainer(classes);
        MemberRepositoryImpl memberRepositoryImpl = container.getBean(MemberRepositoryImpl.class);

        assertThat(memberRepositoryImpl).isNotNull();
    }

    @Test
    @DisplayName("컨테이너에 클래스타입을 생성자인자로 입력하면 빈객체 생성")
    void createContainer(){
        var classes = new HashSet<Class<?>>();
        classes.add(MemberService.class);
        classes.add(MemberRepositoryImpl.class);

        DiContainer container = new DiContainer(classes);
        MemberRepositoryImpl memberRepositoryImpl = container.getBean(MemberRepositoryImpl.class);
        MemberService MemberService = container.getBean(MemberService.class);

        assertThat(memberRepositoryImpl).isNotNull();
        assertAll(
                () -> assertThat(memberRepositoryImpl).isNotNull(),
                () -> assertThat(MemberService).isNotNull());
    }
}
