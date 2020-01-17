package club.banyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class SearchController {

    @GetMapping("/search")
    String searchBlog(@RequestParam String key){
        return "search key is "+key;
    }
}