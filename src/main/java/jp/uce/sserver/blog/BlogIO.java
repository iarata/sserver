package jp.uce.sserver.blog;

import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BlogIO {

    private static final String blogFile = "blogs.json";

    
    // Define findAll() that returns a list of Blogs
    public List<Blog> findAll() {
        return readBlogs();
    }

    // Define findById() that returns a Blog
    public Blog findById(UUID id) {
        List<Blog> blogs = readBlogs();
        for (Blog blog : blogs) {
            if (blog.getId().equals(id)) {
                return blog;
            }
        }
        return null;
    }

    // Define save() that saves a Blog
    public void save(Blog blog) {
        // Map blog to a new instance of Blog
        Blog newBlog = new Blog(blog.getTitle(), blog.getContent());
        List<Blog> blogs = readBlogs();
        // blogs.add(newBlog);

        // Append newBlog to the beginning of blogs
        blogs.add(0, newBlog);
        
        writeBlogs(blogs);
    }

    // Define saveAll() that saves a list of Blogs
    public void saveAll(List<Blog> blogs) {
        writeBlogs(blogs);
    }

    // Define update() that updates a Blog
    public void update(Blog blog) {
        List<Blog> blogs = readBlogs();
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getId().equals(blog.getId())) {
                blogs.set(i, blog);
                break;
            }
        }
        writeBlogs(blogs);
    }

    // Define delete() that deletes a Blog
    public void delete(UUID id) {
        List<Blog> blogs = readBlogs();
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getId().equals(id)) {
                blogs.remove(i);
                break;
            }
        }
        writeBlogs(blogs);
    }

    // Define count() that returns the number of Blogs
    public int count() {
        return readBlogs().size();
    }

    // Define findByTitle() that returns a Blog
    public Blog findByTitle(String title) {
        List<Blog> blogs = readBlogs();
        for (Blog blog : blogs) {
            if (blog.getTitle().equals(title)) {
                return blog;
            }
        }
        return null;
    }

    // Define a function to read the blog file which is a json file and return a list of blogs
    public List<Blog> readBlogs() {
        // Read the json file
        String json = readJsonFile(blogFile);
        // Convert the json file to a list of blogs
        return convertJsonToBlogs(json);
    }

    // Define a function to write the blog file which is a json file and write the list of blogs
    public void writeBlogs(List<Blog> blogs) {
        // Convert the list of blogs to a json file
        String json = convertBlogsToJson(blogs);
        // Write the json file
        writeJsonFile(blogFile, json);
    }


    // Define a function to read the json file
    private String readJsonFile(String fileName) {
        String json = "";
        try {
            // Read the json file
            json = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    // Define a function to write the json file
    private void writeJsonFile(String fileName, String json) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(json);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define a function to convert the json file to a list of blogs, use Gson to convert the json file to a list of blogs
    private List<Blog> convertJsonToBlogs(String json) {
        // Create a Gson object
        Gson gson = new Gson();
        // Convert the json file to a list of blogs
        return gson.fromJson(json, new TypeToken<List<Blog>>(){}.getType());
    }

    // Define a function to convert the list of blogs to a json file, use Gson to convert the list of blogs to a json file
    private String convertBlogsToJson(List<Blog> blogs) {
        // Create a Gson object
        Gson gson = new Gson();
        // Convert the list of blogs to a json file
        return gson.toJson(blogs);
    }
}
