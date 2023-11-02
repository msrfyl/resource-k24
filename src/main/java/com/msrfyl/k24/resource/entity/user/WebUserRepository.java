package com.msrfyl.k24.resource.entity.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebUserRepository extends CrudRepository<WebUser, Integer> {

    @NotNull
    @Override
    public List<WebUser> findAll();

    public WebUser findByUsername(String username);
}
