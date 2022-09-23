package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/idCheck")
    public boolean member(@RequestParam String id){
        return memberRepository.existsById(id);

    }
}
