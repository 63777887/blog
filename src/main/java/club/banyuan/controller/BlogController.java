package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
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
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService itemService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "blogs/create")
    public String showCreatePage(HttpSession session,
                                 HttpServletRequest request){
        User currentUser=(User) session.getAttribute("CURRENT_USER");
        if (currentUser!=null){
            return "create";
        }else {
            String currentUri=request.getRequestURI();
            return "redirect:/login?next="+currentUri;
        }
    }

    @PostMapping(value = "/blogs")
    public String createBlog(@RequestParam String title,
                             @RequestParam String content,
                             HttpSession session){
        User currentUser=(User) session.getAttribute("CURRENT_USER");
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(currentUser.getId());
        blogService.createBlog(blog);
        System.out.println(blog);
        return "redirect:/blogs/"+blog.getId();
    }


    @GetMapping("/blogs/{blogId}")
    String getItem(@PathVariable("blogId") Integer blogID,
                   Model model){
        Blog blog=blogService.getBlogByBlogId(blogID);
        User user=userService.getUserById(blogID);
        List<Comment> comments=itemService.findBlogComment(blogID);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",comments);
        model.addAttribute("user",user);
        return "item";
    }


    @GetMapping("/user/{username}")
    String getUserBlogs(
            @PathVariable String username,
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @RequestParam(required = false,defaultValue = "2") Integer size,
            Model model){
        //  username - > user
        //  getUserByUsername
        //  user.id - > List<Blog> - > blogs
        //  getBlogsByUserId
        User user = userService.getUserByName(username);
//        List<Blog> blogs= blogService.getPagedBlogsByUsername(username,page,size);
//        boolean hasPre=page==1?false:true;
//        List<Blog> nextPageBlogs = blogService.getPagedBlogsByUsername(username, page+1, size);
//        boolean hasNext = (nextPageBlogs.size() == 0) ? false : true;
//        model.addAttribute("blogs", blogs);
//        model.addAttribute("user", user);
//        model.addAttribute("hasPre",hasPre);
//        model.addAttribute("hasNext",hasNext);
//        model.addAttribute("page",page);
        PageInfo pageInfo=blogService.pageUserBlog(username,page,size);
        model.addAttribute("user",user);
        model.addAttribute("blogs",pageInfo);

        return "list";
    }
}