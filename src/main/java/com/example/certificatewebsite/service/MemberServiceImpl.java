package com.example.certificatewebsite.service;

import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void insertMember(String id, String password, String email, String phone_number) {
        Member member = Member.builder().id(id).password(password).email(email).phone_number(phone_number).build();
        memberRepository.save(member);
    }
}
