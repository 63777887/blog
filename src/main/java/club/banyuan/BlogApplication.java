package club.banyuan;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.dao.BlogDao;
import club.banyuan.dao.CommentDao;
import club.banyuan.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan("club.banyuan.dao")
public class BlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        UserDao userDao = context.getBean(UserDao.class);
        User user=userDao.selectUserByName("用户9");
        System.out.println(userDao.selectUserByName("用户9").toString());

        BlogDao blogDao = context.getBean(BlogDao.class);
        List<Blog> blogs = blogDao.selectBlogByUserName("用户9");
        System.out.println(blogs.toString());

        CommentDao commentDao =context.getBean(CommentDao.class);
        System.out.println(commentDao.toString());
        User user1=userDao.selectUserByBlogId(295);
        System.out.println(user1.toString());

//        Blog blog = new Blog();
//        blog.setUserId(user1.getId());
//        blog.setContent("contentzzzz");
//        blog.setTitle("titlezzzz");
//        blogDao.insertBlog(blog);
//        System.out.println(blog);
//        blogDao.insertBlog("titleSds","contentLdsjiujkdf",user.getId());
    }
}