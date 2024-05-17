package ch.hftm.blogproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Blog {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;

    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }


    public void setId(Long it) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Blog(Long Id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
