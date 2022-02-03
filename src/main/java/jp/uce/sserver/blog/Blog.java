package jp.uce.sserver.blog;
/*
*   The class of the blog
*   
*   @author Hajebrahimi Alireza
*/

import java.time.LocalDate;
import java.util.UUID;


public class Blog {

    // --* Variables *--
    private UUID id;
    private String title;
    private String content;
    private String date;

    // --* Constructors *--
    public Blog() {
    }

    public Blog(UUID id, String title, String content, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date.toString();
    }

    public Blog(String title, String content, LocalDate date) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.date = date.toString();
    }

    public Blog(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.date = LocalDate.now().toString();
    }


    // --* Getters and Setters *--
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    // Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Content
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    // Date
    public void setDate(LocalDate date) {
        this.date = date.toString();
    }
    public LocalDate getDate() {
        return LocalDate.parse(date);
    }

    // --* Methods *--
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

}
