package jp.uce.sserver.blog;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> geBlogs() {
        return blogRepository.findAll();
    }

    public void createBlog(Blog blog) {
        Blog blogInDb = blogRepository.findBlogTitle(blog.getTitle());
        if (blogInDb != null) {
            throw new IllegalStateException("Blog already exists. Try to change the title.");
        }
        blogRepository.save(blog);
    }

    public void deleteBlog(String id) {
        blogRepository.deleteById(UUID.fromString(id));
    }
}
