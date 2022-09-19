package com.example.certificatewebsite.controller;

import com.example.certificatewebsite.entity.Board;
import com.example.certificatewebsite.entity.Member;
import com.example.certificatewebsite.repository.MemberRepository;
import com.example.certificatewebsite.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@SessionAttributes("memberID")
@Controller
public class MyController {

    private final BoardService boardService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    public MyController(BoardService board) {
        this.boardService = board;
    }

    @RequestMapping("/")
    public String initialize(){
        return "home";
    }

    @RequestMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join2")
    public String member(@RequestParam String id, @RequestParam String password, @RequestParam String email, @RequestParam String phone_number, Model model){

        Member member = Member.builder().id(id).password(password).email(email).phone_number(phone_number).build();
        memberRepository.save(member);

        return "login";
    }

    @RequestMapping("login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String id, @RequestParam String password, Model model){
        Optional<Member> member = memberRepository.findById(id);

        if(!member.isPresent()){
            model.addAttribute("error","존재하지 않는 아이디입니다.");
            return "login";
        }
        if(!member.get().getPassword().equals(password)) {
            model.addAttribute("error", "아이디와 비밀번호가 일치하지 않습니다.");
            return "login";
        }
        model.addAttribute("memberID",member.get().getId());
        return "redirect:/";
    }

    @PostMapping("/")
    public String loginhome(@ModelAttribute("memberID")String memberID, Model model){
        if(memberID == null) {
            model.addAttribute("memberID", null);
        }else{
            model.addAttribute("memberID",memberID);
        }
        return "home";
    }

    @GetMapping("logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("memberInfo")
    public String memberInfo(@ModelAttribute("memberID") String memberID,Model model){
        Optional<Member> member = memberRepository.findById(memberID);
        model.addAttribute("memberID",memberID);
        model.addAttribute("member",member.get());
        return "memberInfo";
    }

    @RequestMapping(value = "/commentBoard/{bid}")
    public String commentBoardPage(Model model, @PathVariable("bid") Long bid) {
        Board board = this.boardService.getBoard(bid);
        model.addAttribute("board", board);
        return "commentBoard";
    }

    @RequestMapping("boardList")
    public String boardListPage(@ModelAttribute("memberID") String memberID, Model model, @PageableDefault(page=0, size = 5, sort = "bid", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword){

        Page<Board> boardList= null;

        if(searchKeyword == null){
            boardList = boardService.getBoardList(pageable);
        }
        else{
            boardList = boardService.searchBoardList(searchKeyword,pageable);
        }

        int nowPage= boardList.getPageable().getPageNumber();

        int startPage=Math.max(nowPage -4,1);
        int endPage=Math.min(nowPage+5,boardList.getTotalPages());
        int previous = nowPage-1;
        int next = nowPage+1;

        model.addAttribute("memberID", memberID);
        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previous",previous);
        model.addAttribute("next",next);

        return "boardList";
    }

    @RequestMapping("deleteBoard")
    public String deleteBoard(@RequestParam Long bid){
        boardService.deleteBoard(bid);
        return "redirect:boardList";
    }

    @RequestMapping("boardInput")
    public String boardInputPage(){

        return "boardInput";
    }

    @RequestMapping("writeSucces")
    public String writeSucces(@ModelAttribute("memberID") String memberID, @RequestParam String title, @RequestParam String content){
//        boardService.insertBoard(memberID, board_title, board_content);
        this.boardService.input(memberID, title, content);
        return "redirect:/boardList";
    }

    @PostMapping("/comment/write/{bid}")
    public String writecomment(Model model, @PathVariable("bid") Long bid, @RequestParam String comment){
        Board board = this.boardService.getBoard(bid);
        this.boardService.writecomment(board, comment);
        return String.format("redirect:/commentBoard/%s", bid);
    }



    @RequestMapping("test")
    public String testPage(){
        return "test";
    }


}
