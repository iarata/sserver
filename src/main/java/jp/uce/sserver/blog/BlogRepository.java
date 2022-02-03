package jp.uce.sserver.blog;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository extends BlogIO {

    // Define findByTitle method
    public Blog findBlogTitle(String title) {
        return super.findByTitle(title);
    }

    // Define deleteById method
    public void deleteById(UUID id) {
        // Get all blogs and check if the blog with the given id exists
        List<Blog> blogs = super.findAll();
        boolean exist = false;
        for (Blog blog : blogs) {
            if (blog.getId().equals(id)) {
                exist = true;
                break;
            }
        }

        // If the blog with the given id exists, delete it
        if (!exist) {
            throw new IllegalStateException("Blog does not exist.");
        }
        super.delete(id);
    }
}
