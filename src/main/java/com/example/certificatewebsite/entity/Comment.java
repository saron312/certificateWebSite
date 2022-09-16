package com.example.certificatewebsite.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="comment")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bid;

    @Column(nullable = false)
    private String comment;

    private LocalDateTime createDate;

    @ManyToOne
    private Board board;
}
