package com.example.dicontainer.container;

import com.example.dicontainer.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerTest {

    @Test
    @DisplayName("컨테이너에 클래스타입을 생성자인자로 입력하면 빈객체 생성")
    void createContainer(){
        var classes = new HashSet<Class<?>>();
        classes.add(MemberRepository.class);

        DiContainer container = new DiContainer(classes);
        MemberRepository memberRepository = container.getBean(MemberRepository.class);

        assertThat(memberRepository).isNotNull();
    }
}
