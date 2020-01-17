package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> findBlogComment(Integer blogId){
        return commentDao.selectCommentByBlogId(blogId);
    }
    public void createComment(Comment comment){
        commentDao.insertComment(comment);
    }
}
