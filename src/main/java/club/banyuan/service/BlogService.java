package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }




    public List<Blog> getBlogsByUserName(String userName){
        return blogDao.selectBlogByUserName(userName);
    }

    public List<Blog> getPagedBlogsByUsername(String username,Integer page,Integer size){
        Integer offest=(page-1)*size;
        return blogDao.selectBlogByUserNameWithPageInfo(username,offest,size);
    }

    public PageInfo<Blog> pageUserBlog(String username, Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Blog> allBlogs= blogDao.selectBlogByUserName(username);
        return new PageInfo<Blog>(allBlogs);
    }
    public Blog getBlogByBlogId(Integer blogId) {
        return blogDao.selectBlogById(blogId);
    }
    public void createBlog(Blog blog){
        blogDao.insertBlog(blog);
    }

    public void deleteBlogByTitle(String title){
        blogDao.deleteBlog(title);
    }
}
