package com.example.certificatewebsite.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    // 질문 하나에 여러개 답변이 작성될 수 있는데 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해 Remove 사용
    private List<Board> boardList;

}
