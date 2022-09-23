package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("memberID")
@Controller
public class MyPageController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("memberInfo")
    public String memberInfo(@ModelAttribute("memberID") String memberID, Model model){
        Member member = memberRepository.findById(memberID).get();
        model.addAttribute("memberID",memberID);
        model.addAttribute("memberEmail",member.getEmail());
        model.addAttribute("memberPhoneNumber",member.getPhone_number());
        return "memberInfo";
    }
}
