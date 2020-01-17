package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    UserService userService;


    @GetMapping
    String showLoginHtml(@RequestParam(required = false) String next,
                         HttpSession session){
        if (next!=null){
            session.setAttribute("NEXT_URI",next);
        }
        return "login";
    }

    @PostMapping
    String login(@RequestParam String username,
                 @RequestParam String password,
                 HttpSession session) throws UnsupportedEncodingException {
        User user=userService.getUserByName(username);
        String nextUri=(String) session.getAttribute("NEXT_URI");
        if (user!=null && password.equals(user.getPassword())){
            session.setAttribute("CURRENT_USER",user);
            //如果有next，跳转到next
            if (nextUri!=null){
                session.removeAttribute("NEXT_URI");
                return "redirect:"+nextUri;
            }
            //没有next，跳转到user
            return "redirect:"+URLEncoder.encode(username,"UTF-8")+"/admin";
        }else {

        }
        return "";
    }
}
