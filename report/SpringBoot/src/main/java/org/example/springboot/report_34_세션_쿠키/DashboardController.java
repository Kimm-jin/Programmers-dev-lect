package org.example.springboot.report_34_세션_쿠키;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardController {

    private final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/dashboard")
    public String dashboard(
        HttpSession session,
        Model model,
        @CookieValue(value="lastVisit", required=false)String lastVisit,
        @CookieValue(value = "theme", defaultValue = "light") String theme,
        HttpServletResponse response
    ){

        String username = (String)session.getAttribute("username");
        if(username==null)return "redirect:/login";

        model.addAttribute("username",username);
        model.addAttribute("theme", theme);

        // 지난 방문 시각
        if(lastVisit!=null){
            long visited = Long.parseLong(lastVisit);
            String lastVisitText = Instant.ofEpochMilli(visited)
                    .atZone(ZoneId.systemDefault()).format(FMT);
            model.addAttribute("lastVisit",lastVisitText);
        }

        Cookie visit = new Cookie("lastVisit", String.valueOf(System.currentTimeMillis()));
        visit.setMaxAge(60*60*24*7); // 7일
        visit.setPath("/");
        visit.setHttpOnly(true);
        response.addCookie(visit);

        return "dashboard";
    }

    @GetMapping("/theme")
    public String setTheme(
            @RequestParam String mode,
            HttpServletResponse response
    ){
        String value = "dark".equals(mode) ? "dark" : "light";
        Cookie theme = new Cookie("theme", value);
        theme.setMaxAge(60*60*24*7);
        theme.setPath("/");
        response.addCookie(theme);
        return "redirect:/dashboard";
    }

}
