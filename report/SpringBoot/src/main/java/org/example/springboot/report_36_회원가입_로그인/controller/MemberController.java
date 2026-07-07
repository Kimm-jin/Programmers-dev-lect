package org.example.springboot.report_36_회원가입.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/join")
    public String join() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String login() {
        return "sign-in";
    }

    private void setSession(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
    }
}
