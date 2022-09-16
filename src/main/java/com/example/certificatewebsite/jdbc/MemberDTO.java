package com.example.certificatewebsite.jdbc;

import lombok.Data;

@Data
public class MemberDTO {
    private String id;
    private String password;
    private String phone_number;
    private String email;
}
