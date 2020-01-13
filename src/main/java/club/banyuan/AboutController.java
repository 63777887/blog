package club.banyuan;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AboutController {
    @GetMapping("/about")
    String getAbout(){
        return "about";
    }
}
