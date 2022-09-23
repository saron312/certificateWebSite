package com.example.certificatewebsite.service;

import com.example.certificatewebsite.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {
    Page<Board> getBoardList(Pageable pageable);
    Page<Board> searchBoardList(String selectKeyword, String searchKeyword, Pageable pageable);
    //    void insertBoard(String id, String title, String content);
    void deleteBoard(Long bid);
    Board getBoard(Long bid);
    void insertBoardList(String id, String title, String content, String certificate);
    void writeComment(Board board, String comment);

    void updateViewCount(Long bid);
}
