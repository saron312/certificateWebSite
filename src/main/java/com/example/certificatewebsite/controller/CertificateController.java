package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Board;
import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import com.example.certificatewebsite.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@SessionAttributes("memberID")
@Controller
public class CertificateController {

    private final BoardService boardService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    public CertificateController(BoardService board) {
        this.boardService = board;
    }

    @GetMapping("/")
    public String initialize(){
        return "/home";
    }

    @GetMapping("/home")
    public String homePage(){
        return "/home";
    }

}
