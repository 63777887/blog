package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
@Autowired
private BlogService blogService;
    @Autowired
    private CommentService itemService;
    @Autowired
    private UserService userService;

    @GetMapping("/{username}/admin")
String getUserBlogs(
                @PathVariable String username,
                @RequestParam(required = false,defaultValue = "1") Integer page,
                @RequestParam(required = false,defaultValue = "2") Integer size,
                Model model,
                HttpSession session,
                HttpServletRequest request){
    User user = userService.getUserByName(username);
    PageInfo pageInfo=blogService.pageUserBlog(username,page,size);
    model.addAttribute("user",user);
    model.addAttribute("blogs",pageInfo);
        User currentUser=(User) session.getAttribute("CURRENT_USER");
        if (currentUser!=null){
            return "admin";
        }else {
            String currentUri=request.getRequestURI();
            return "redirect:/login?next="+currentUri;
        }
    }

    @PostMapping("/{username}/admin")
    String deleteBlogByTitle(@RequestParam String title){
        blogService.deleteBlogByTitle(title);
        return "admin";
    }
}
