package com.example.certificatewebsite.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Member")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String email;

}
