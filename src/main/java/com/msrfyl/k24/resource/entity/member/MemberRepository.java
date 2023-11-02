package com.msrfyl.k24.resource.entity.member;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    @NotNull
    @Override
    public List<Member> findAll();

    public Member findByEmail(String email);

    public List<Member> findByName(String name);
}
