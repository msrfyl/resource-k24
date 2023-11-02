package com.msrfyl.k24.resource.controller;

import com.msrfyl.k24.resource.entity.member.Member;
import com.msrfyl.k24.resource.entity.member.MemberRepository;
import com.msrfyl.k24.resource.entity.user.WebUser;
import com.msrfyl.k24.resource.entity.user.WebUserRepository;
import com.msrfyl.k24.resource.general.HandleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@BasePathAwareController
public class GeneralController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("authenticate")
    private ResponseEntity<EntityModel<Object>> authenticate(@RequestParam Map<String, String> user) {
        System.out.println("accessing authenticate");
        String username = "";
        String password = "";

        if (user.get("username") != null ) {
            username = user.get("username");
        } else {
            throw new HandleException("username can't be empty");
        }

        if (user.get("password") != null ) {
            password = user.get("password");
        } else {
            throw new HandleException("password can't be empty");
        }
        System.out.println("username: " +username);
        System.out.println("password: " +password);

        if (username.startsWith("user-")) {
            WebUser u = webUserRepository.findByUsername(username.replace("user-", ""));
            if (u != null) {
                if (passwordEncoder.matches(password, u.getPassword())) {
                    return ResponseEntity.ok(EntityModel.of(u));
                }
            }
        } else if (username.startsWith("member-")) {
            Member u = memberRepository.findByEmail(username.replace("member-", ""));
            if (u != null) {
                if (passwordEncoder.matches(password, u.getPassword())) {
                    return ResponseEntity.ok(EntityModel.of(u));
                }
            }
        }


        throw new HandleException("username and password did not match");

    }


}
