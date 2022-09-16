package com.example.certificatewebsite.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="board")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bid;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    // 질문 하나에 여러개 답변이 작성될 수 있는데 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해 Remove 사용
    private List<Comment> commentList;
}
