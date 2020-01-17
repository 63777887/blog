package club.banyuan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/user/about")
    String getAbout(){
        return "about";
    }
}
