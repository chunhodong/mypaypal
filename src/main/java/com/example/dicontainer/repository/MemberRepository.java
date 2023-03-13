package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
