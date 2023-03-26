package com.example.dicontainer.container;

import com.example.dicontainer.repository.*;
import com.example.dicontainer.service.BookService;
import com.example.dicontainer.service.MemberService;
import com.example.dicontainer.service.RentService;
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
        classes.add(BookService.class);
        classes.add(MemberRepositoryImpl.class);
        classes.add(BookRepositoryImpl.class);
        classes.add(RentRepositoryImpl.class);
        classes.add(RentService.class);

        DiContainer container = new DiContainer(classes);
        MemberRepository memberRepository = container.getBean(MemberRepository.class);
        BookRepository bookRepository = container.getBean(BookRepository.class);
        MemberService memberService = container.getBean(MemberService.class);
        BookService bookService = container.getBean(BookService.class);

        assertAll(
                () -> assertThat(memberRepository).isNotNull(),
                () -> assertThat(bookRepository).isNotNull(),
                () -> assertThat(memberService).isNotNull(),
                () -> assertThat(bookService).isNotNull());
    }
}
