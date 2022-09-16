package com.example.certificatewebsite.service;

import com.example.certificatewebsite.entity.Board;
import com.example.certificatewebsite.entity.Comment;
import com.example.certificatewebsite.exception.DataNotFoundException;
import com.example.certificatewebsite.repository.BoardRepository;
import com.example.certificatewebsite.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Board> getBoardList(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards;
    }

    @Override
    public void deleteBoard(Long bid) {
        boardRepository.deleteById(bid);
    }

    @Override
    public Page<Board> searchBoardList(String searchKeyword, Pageable pageable) {
        Page<Board> boards = boardRepository.findByTitleContaining(searchKeyword, pageable);
        return boards;
    }

//    @Override
//    public void insertBoard(String id, String title, String content) {
//        Board board = Board.builder().id(id).title(title).content(content).build();
//        boardRepository.save(board);
//    }

    @Override
    public Board getBoard(Long bid) {
        Optional<Board> board = this.boardRepository.findById(bid);
        if(board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("데이터가 없습니다.");
        }
    }

    @Override
    public void input(String id, String title, String content) {
        Board b = new Board();
        b.setId(id);
        b.setTitle(title);
        b.setContent(content);
        b.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(b);
    }

    @Override
    public void writecomment(Board board, String comment) {
        Comment c1 = new Comment();
        c1.setComment(comment);
        c1.setCreateDate(LocalDateTime.now());
        c1.setBoard(board);
        this.commentRepository.save(c1);
    }
}

