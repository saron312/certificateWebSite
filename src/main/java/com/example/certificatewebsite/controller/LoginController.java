package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@SessionAttributes("memberID")
@Controller
public class LoginController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, Model model){
        Optional<Member> member = memberRepository.findById(id);

        if(!member.isPresent()){
            model.addAttribute("error","존재하지 않는 아이디입니다.");
            return "/login";
        }
        if(!member.get().getPassword().equals(password)) {
            model.addAttribute("error", "아이디와 비밀번호가 일치하지 않습니다.");
            return "/login";
        }
        model.addAttribute("memberID",member.get().getId());
        return "redirect:/";
    }

    @PostMapping("/")
    public String checkLogin(@ModelAttribute("memberID")String memberID, Model model){
        if(memberID == null) {
            model.addAttribute("memberID", null);
        }else{
            model.addAttribute("memberID",memberID);
        }
        return "/home";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/home";
    }
}
