package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService itemService;
    @Autowired
    private UserService userService;

    @GetMapping("/blogs/{blogId}/comments")
    public String createComment(@PathVariable Integer blogId,
                                HttpSession session
    ){
        User currentUser=(User) session.getAttribute("CURRENT_USER");
            String commentContent=(String) session.getAttribute("COMMENT_CONTENT");
            Comment comment = new Comment();
            comment.setContent(commentContent);
            comment.setUserId(currentUser.getId());
            comment.setBlogId(blogId);
            itemService.createComment(comment);
            return "redirect:/blogs/" + blogId;
    }


    @PostMapping(value = "/blogs/{blogId}/comments")
    public String createComment(@RequestParam String content,
                                @PathVariable Integer blogId,
                                HttpSession session,
                                HttpServletRequest request){
        User currentUser=(User) session.getAttribute("CURRENT_USER");
        if (currentUser!=null) {
            Blog blog = blogService.getBlogByBlogId(blogId);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUserId(currentUser.getId());
            comment.setBlogId(blogId);
            itemService.createComment(comment);
            return "redirect:/blogs/" + blogId;
        }else {
            //  comment content -->session
            session.setAttribute("COMMENT_CONTENT",content);
            //  redirect next ??
            return "redirect:/login?next="+request.getRequestURI();
        }
    }
}