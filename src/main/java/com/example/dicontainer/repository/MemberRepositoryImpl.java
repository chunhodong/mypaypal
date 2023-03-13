package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Long, Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return members.get(id);
    }
}
