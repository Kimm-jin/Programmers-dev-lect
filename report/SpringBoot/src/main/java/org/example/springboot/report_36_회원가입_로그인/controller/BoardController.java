package org.example.springboot.report_36_회원가입_로그인.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/")
    public String boardList(HttpSession session, Model model){
        setSession(session, model);
        return "board-list";
    }

    @GetMapping("/write")
    public String write(HttpSession session, Model model){
        setSession(session, model);
        return "/board-write";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/members/login";
    }

    private void setSession(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
    }
}
