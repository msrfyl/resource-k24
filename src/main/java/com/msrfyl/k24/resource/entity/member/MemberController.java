package com.msrfyl.k24.resource.entity.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@BasePathAwareController
@RequestMapping("members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("findBy/{name}")
    private ResponseEntity<Object> findByName(
            @PathVariable String name
    ) {
        List<Member> m = memberRepository.findByName(name);
        return ResponseEntity.ok(m);
    }

}
