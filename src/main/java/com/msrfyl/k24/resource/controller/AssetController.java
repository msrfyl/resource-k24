package com.msrfyl.k24.resource.controller;

import com.msrfyl.k24.resource.entity.member.Member;
import com.msrfyl.k24.resource.entity.member.MemberRepository;
import com.msrfyl.k24.resource.general.HandleException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class AssetController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "assets/member/{id}/photo", produces = {MediaType.IMAGE_PNG_VALUE})
    private ResponseEntity<InputStreamResource> memberPhoto(
            @PathVariable Integer id, HttpServletResponse response
    ) throws IOException {
        Member m = memberRepository.findById(id).orElseThrow(() -> new HandleException("member id not found"));
        if (m.getPhoto() != null) {
            Resource resource = resourceLoader.getResource("file:" + m.getPhoto());
            if (resource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(resource.getInputStream()));
            }
        }
        throw new HandleException("member photo not found");
    }

}
