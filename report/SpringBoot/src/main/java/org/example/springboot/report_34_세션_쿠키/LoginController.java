package org.example.springboot.report_34_세션_쿠키;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            HttpSession session,
            Model model
    ){
        String username = (String) session.getAttribute("username");
        if(username!=null) return "redirect:/dashboard";
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        HttpSession session
    ){
        session.setAttribute("username",username);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


}
