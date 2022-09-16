package com.example.certificatewebsite.repository;


import com.example.certificatewebsite.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

}
