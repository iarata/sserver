package jp.uce.sserver.blog;
/*
*   The controllers of the blog
*   
*   @author Hajebrahimi Alireza
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @GetMapping
    public List<Blog> geBlogs() {
        return blogService.geBlogs();
    }

    @PostMapping
    public void createBlog(@RequestBody Blog blog) {
        blogService.createBlog(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
    }
}

