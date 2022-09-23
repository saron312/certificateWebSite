package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import com.example.certificatewebsite.service.BoardService;
import com.example.certificatewebsite.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {

    private final MemberService memberService;

    @Autowired
    public JoinController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @PostMapping("/join")
    public String joinSuccess(@RequestParam String id, @RequestParam String password, @RequestParam String email, @RequestParam String phone_number, Model model){

        memberService.insertMember(id, password, email, phone_number);

        return "login";
    }
}
