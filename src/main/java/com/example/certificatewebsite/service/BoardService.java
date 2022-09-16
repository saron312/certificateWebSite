package com.example.certificatewebsite.service;

import com.example.certificatewebsite.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {
    Page<Board> getBoardList(Pageable pageable);
    Page<Board> searchBoardList(String searchKeyword, Pageable pageable);
    //    void insertBoard(String id, String title, String content);
    void deleteBoard(Long bid);
    Board getBoard(Long bid);
    void input(String id, String title, String content);

    void writecomment(Board board, String comment);
}
