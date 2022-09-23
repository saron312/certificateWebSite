package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Board;
import com.example.certificatewebsite.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("memberID")
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService board) {
        this.boardService = board;
    }

    @GetMapping("/boardList")
    public String boardListPage(@ModelAttribute("memberID") String memberID, Model model, @PageableDefault(page=0, size = 5, sort = "bid", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> boardList= null;

        boardList = boardService.getBoardList(pageable);

        int nowPage= boardList.getPageable().getPageNumber();

        int startPage=Math.max(nowPage -4,1);
        int endPage=Math.min(nowPage+5,boardList.getTotalPages());
        int previous = nowPage-1;
        int next = nowPage+1;

        String checkedSearch = "false";
        model.addAttribute("checkedSearch", checkedSearch);

        model.addAttribute("memberID", memberID);

        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previous",previous);
        model.addAttribute("next",next);

        return "/boardList";
    }

    @PostMapping("/boardList")
    public String searchBoardList(@RequestParam String searchKeyword, @RequestParam String selectKeyword, @PageableDefault(page=0, size = 5, sort = "bid", direction = Sort.Direction.DESC) Pageable pageable
            , Model model, @ModelAttribute("memberID") String memberID) {

        Page<Board> searchPage = null;
        searchPage = boardService.searchBoardList(selectKeyword,
                searchKeyword, pageable);

        int nowPage = searchPage.getPageable().getPageNumber();
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, searchPage.getTotalPages());
        int previous = nowPage - 1;
        int next = nowPage + 1;

        String checkedSearch = "true";
        model.addAttribute("checkedSearch", checkedSearch);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("selectKeyword", selectKeyword);

        model.addAttribute("memberID", memberID);

        model.addAttribute("boardList", searchPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previous", previous);
        model.addAttribute("next", next);

        return "/boardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoardList(@RequestParam Long bid){
        boardService.deleteBoard(bid);
        return "redirect:/boardList";
    }

    @GetMapping("/boardInput")
    public String boardInputPage(){

        return "/boardInput";
    }

    @PostMapping("/boardInput")
    public String boardInput(@ModelAttribute("memberID") String memberID,
                              @RequestParam String title, @RequestParam String content,
                              @RequestParam String certificate){

        this.boardService.insertBoardList(memberID, title, content, certificate);
        return "redirect:/boardList";
    }

    @RequestMapping(value = "/commentBoard/{bid}")
    public String commentBoardPage(@ModelAttribute("memberID") String memberID, Model model, @PathVariable("bid") Long bid) {
        Board board = this.boardService.getBoard(bid);
        this.boardService.updateViewCount(bid);
        model.addAttribute("board", board);

        String sessionMemberID = memberID;
        model.addAttribute("sessionMemberID", sessionMemberID);
        return "/commentBoard";
    }

    @PostMapping("/comment/write/{bid}")
    public String writeComment(@ModelAttribute("memberID") String memberID,Model model, @PathVariable("bid") Long bid, @RequestParam String comment){

        Board board = this.boardService.getBoard(bid);
        this.boardService.writeComment(board, comment);
        String sessionMemberID = memberID;
        model.addAttribute("sessionMemberID", sessionMemberID);
        return String.format("redirect:/commentBoard/%s", bid);
    }
}
